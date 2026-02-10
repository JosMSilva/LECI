
# Projeto Final Dispositivos Conectados

Este presente projeto visa o desenvolvimento de um sistema embebido autónomo para monitorização ambiental, baseado na arquitetura *RISC-V* através do microcontrolador **ESP32-C6**.

O sistema proposto integra a recolha de dados de temperatura e humidade (sensor DHT20) com uma interface humano-computador local (ecrã TFT e LED RGB) e conectividade remota via protocolo MQTT. Adicionalmente, o sistema destaca-se pela robustez, incorporando tolerância a falhas de rede (Buffering Offline), capacidade de reconfiguração em tempo real (Hot-Swap) e auditabilidade, utilizando armazenamento local em cartão microSD para registo de logs.


# Arquitetura

O projeto adota uma arquitetura modular baseada no sistema operativo de tempo real FreeRTOS, garantindo a gestão eficiente de tarefas concorrentes e a otimização do consumo energético através de mecanismos baseados em eventos (Event-Driven). Foram desenvolvidos diversos módulos independentes que dialogam entre si utilizando Direct Task Notifications.

## Aquitetura de Hardware
<img width="1077" height="590" alt="image" src="https://github.com/user-attachments/assets/aa212cc3-80e6-49db-b031-d4c3a4548738" />



## Aquitetura de Software
<img width="1184" height="513" alt="image" src="https://github.com/user-attachments/assets/e00b00e3-0c69-44d4-a985-0a4eca4a4e40" />



## Modulos de Hardware
Esta secção detalha os componentes físicos que constituem a arquitetura do sistema, justificando a sua escolha e o método de interligação.

### Unidade Central de Processamento (ESP32-C6)
O núcleo do sistema é o SoC ESP32-C6 da Espressif. Baseado na arquitetura RISC-V de 32 bits, este microcontrolador foi selecionado pelas suas capacidades nativas de conetividade Wi-Fi 6 (802.11ax) e eficiência energética, essenciais para aplicações IoT. É responsável pela gestão de todos os periféricos e protocolos de rede.

### Sensor DHT20
A aquisição de dados ambientais é realizada pelo sensor digital DHT20. Este módulo comunica via barramento I2C, fornecendo leituras calibradas de temperatura e humidade relativa. Atua como o "pacemaker" do sistema, ditando a frequência de amostragem, configurada pelo utilizador.

### Interface Visual (Ecrã TFT)
Para a apresentação local de dados, utiliza-se um ecrã TFT. A comunicação é estabelecida via barramento SPI, permitindo a atualização fluida de gráficos, partilhado com o cartão SD. O sistema inclui controlo de brilho por hardware, onde o pino de Backlight é gerido exclusivamente por um canal PWM (LEDC), ajustado via ADC.

### Armazenamento Persistente (Cartão MicroSD)
Utiliza o sistema de ficheiros FATFS via SPI. Possui dupla função:

   - Configuração Dinâmica: Permite alterar credenciais Wi-Fi, Broker MQTT e tempos de amostragem editando o ficheiro config.json, sem recompilar o código.
   - Datalogging: Regista o histórico de leituras para auditoria local.

### Interface de Controlo e Estado
   - Potenciómetro: Ligado a uma entrada ADC do ESP32, permite ao utilizador ajustar o duty cycle do PWM que controla a retroiluminação do ecrã, otimizando o consumo energético.
   - LED RGB: controlado por GPIO/PWM, serve como semáforo de estado do sistema (Verde: Normal, Amarelo: Transmissão, Vermelho: Erro), oferecendo feedback visual imediato.

## Modulos de Software
A arquitetura de software baseia-se em tarefas (Tasks) independentes do FreeRTOS, sincronizadas através de Task Notifications. Esta abordagem evita Race Conditions e garante que o processador permanece em modo de baixo consumo (Blocked State) quando não há dados para processar.

### Inicializações (Pré Task Notification)
Antes do ciclo principal de execução, o sistema realiza a inicialização hierárquica dos periféricos.

- _Modulo LED_ : Inicia as suas funções nesta etapa, inicializando com estado de erro até que receba indicação do sistema de que todos os outros modulos estão funcionais.
- _Modulo NVS_ : Requisito do sistema, inicializado nesta etapa devido a necessidade de outro modulos.
- _Modulo WiFi_ e _Modulo DHT20_ : Também inicializados neste momento, estes modulos irão realizar as suas configurações iniciais, no entanto irão num estado adormecido até uma futura Task Notification.
  
### Notificações Iniciais (xDhtTaskHandle, xWifiTaskHandle, xMqttTaskHandle)
O sistema é ativado pela tarefa de Armazenamento, que atua como maestro da configuração inicial.

- _Modulo SD Card_ : Modulo crucial do sistema, nesta fase ele é inicializado e executa uma das primeiras funções do sistema, a leitura do cartão SD enviando Notifications aos restantes modulos.
- _xDhtTaskHandle_ : Esta Notification será sempre enviada, caso a leitura do cartão SD tenha sucedido irá enviar o valor lido do mesmo, caso contrario enviará um valor default para a leitura do sensor (5s).
- _xWifiTaskHandle_ : Notification apenas enviada em caso de sucesso de leitura do SD Card, permitindo ao modulo WiFi inicio de trabalhos, garante também que caso haja alteração dos parametros de WiFi este seja reconfigurado.
- _xMqttTaskHandle_ : Ultima Notification enviada nesta fase, também apenas enviada em caso de sucesso, embora não permita o total funcionamento do modulo MQTT, permite o seu avanço sabendo que o "broker" foi configurado.

### Inicialização de Modulos Secundarios 
Após as primeiras Notifications serem enviadas, e o sistema ter as suas funcionalidades basicas ativas, os modulos secundarios irão ser inicializados.

- _Modulo Display_ : Permitindo a visualização dos dados lidos pelo sensor, o display será inicializado nesta etapa, entrado em modo de "sleep" até receber dados.
- _Modulo MQTT_ : Tendo segurança que o seu "broker" foi definido, o modulo MQTT avança nas suas funções, verificando se a ligação WiFi foi establecida e establecendo conecção.

### Notificações de Dados (xDisplayTaskHandle, xCardTaskHandle, xMqttTaskHandle)
Sendo as ultimas notificações enviadas, completam o funcionamento do sistema, informando os modulos que foram adquiridos novos dados e que podem avançar nas suas funções.

- _xDisplayTaskHandle_ : Eviada após a leitura dos dados do sensor, esta notificação permite que o display avançe e desenhe os dados lidos no gráfico.
- _xCardTaskHandle_ : Notification que garante auditoria do sistema, após receber esta notificação, os dados lidos são escritos no cartão SD de forma encriptada.
- _xMqttTaskHandle_ :
   - *Modo Online*: Publica imediatamente os dados no tópico de telemetria.
   - *Modo Offline*: Se não houver Wi-Fi, armazena os dados num Buffer Circular interno. Assim que a conexão é restabelecida, o buffer é esvaziado para a nuvem, garantindo integridade dos dados.


# Autores e Contribuições 

- 112678 Gonçalo Biscaia Martins, 50%
- 103248 José Miguel Silva, 50%

