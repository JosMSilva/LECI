
public enum Regime {
        PEQUENOALMOCO("Pequeno-Almoço"), MEIAPENSAO("Meia Pensão"), PENSAOCOMPLETA("Pensão Completa"),TUDOINCLUIDO("Tudo Incluido");

        private String friendlyName;

        Regime(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        @Override
        public String toString() {
            return this.friendlyName;
        }

}
