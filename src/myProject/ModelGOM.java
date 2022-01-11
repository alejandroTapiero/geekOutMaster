package myProject;

public class ModelGOM {
    private Dado dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private int tiro, punto, estado, flag;
    private String[] estadotoString;
    private int[] caras;

    public ModelGOM(){
        dado1 = new Dado();
        dado2 = new Dado();
        dado3 = new Dado();
        dado4 = new Dado();
        dado5 = new Dado();
        dado6 = new Dado();
        dado7 = new Dado();
        dado8 = new Dado();
        dado9 = new Dado();
        dado10 = new Dado();
        caras = new int[6];
        estadotoString = new String[6];
        flag = 0;
    }

    public void calcularTiro(){
        caras[0] = dado1.getCara();
        caras[1] = dado1.getCara();
        tiro = caras[0] + caras[1];
    }

    public void determinarJuego(){
        if (flag == 0){
            if (tiro == 1){
                estado = 1;
            }
            else {
                if (tiro == 2){
                    estado = 2;
                }
                else{
                    if (tiro == 3){
                        estado = 3;
                    }
                    else{
                        if (tiro == 4){
                            estado = 4;
                        }
                        else{
                            if (tiro == 5){
                                estado = 5;
                            }
                            else{
                                if (tiro == 6){
                                    estado = 6;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int getTiro() {
        return tiro;
    }

    public String[] getEstadotoString() {
        switch(estado){
            case 1: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "Meeple";
                break;
            case 2: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "Rocket";
                break;
            case 3: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "Dragon";
                break;
            case 4: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "Superhero";
                break;
            case 5: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "Heart";
                break;
            case 6: estadotoString[0] = "Tiro de salida: " + tiro;
                estadotoString[1] = "42";
                break;
        }
        return estadotoString;
    }

    public int[] getCaras() {
        return caras;
    }
}

