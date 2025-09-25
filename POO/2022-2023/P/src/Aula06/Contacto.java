package Aula06;

public class Contacto {
    private static int count = 1;
    private final int id;
    public Contacto(Pessoa pessoa, String email, int telefone){
        assert pessoa != null : "Pessoa inválida";
        if(email.contains("@")){
            if(email.substring(email.indexOf("@") + 1).contains(".")){
                if(email.substring(email.substring(email.indexOf("@") + 1).indexOf(".") + 1).length() >= 2){

                }else{
                    email = null;
                }
            }else{
                email = null;
            }
        }else{
            email = null;
        }
        assert (telefone < 900000000 && telefone > 999999999) && email != null : "Email e Telefone inválido";
        this.id = count++;
        this.pessoa = pessoa;
        this.email = email;
        this.telefone = telefone;
    }

    public int id(){
        return id;
    }

    public Pessoa pessoa(){
        return pessoa;
    }

    public String email(){
        return email;
    }

    public int telefone(){
        return telefone;
    }

    private Pessoa pessoa;
    private String email;
    private int telefone;
    
}
