import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;
/***
 * @author @eddybeyond
 */
public class Pintador {
    /*
    Comandos:
    1-Pluma arriba
    2-Pluma abajo
    3-Gira derecha
    4-Gira izquierda
    5,x-Avanza x espacios
    6-Imprime arreglo
    9-Fin de entrada
    */
    int tamanio = 20;
    int[][] matriz = new int[tamanio][tamanio];
    int x = 0;
    int y = 0;
    int direccion = 0;
    int pluma = 0;

    public static void main(String args[]){
        Pintador p = new Pintador();
        System.out.println("Comandos:\n" +
                "    1-Pluma arriba\n" +
                "    2-Pluma abajo\n" +
                "    3-Gira derecha\n" +
                "    4-Gira izquierda\n" +
                "    5XXX-Avanza XXX espacios\n" +
                "    6-Imprime arreglo\n" +
                "    9-Fin de entrada");
        Scanner scanner = new Scanner(System.in);
        int i=0;
        do {
            String read = scanner.nextLine();
            try {
                i = Integer.parseInt(read);
                p.ejecutar(i);
                System.out.println("");
            }catch (NumberFormatException nfe){
                System.out.println(read + " No es una instrucción válida.");
            }
        }
        while(i!=9);

    }

    private void ejecutar(int ent){
        String val = ent+"";
        int i = Integer.parseInt(val.substring(0,1));
        int q = 0;
        try {
            q = Integer.parseInt(val.substring(1));
        }catch (NumberFormatException nfe){
            //solo hay un caracter
        }
        switch(i){
            case 1:
            case 2:
                this.pluma(i);
                break;
            case 3:
            case 4:
                this.joystick(i);
                break;
            case 5:
                this.avanza(q);
                break;
            case 6:
                this.imprimir();
                break;
            case 9:
                System.out.println("Hasta luego");
                break;
            default:{
                System.out.println(i + " No es una instrucción válida.");
            }
        }
    }

    private void avanza(int q) {
        switch(this.direccion){
            case 0: //derecha
                for(int i = 0; i<q; i++){
                    if(this.y<this.tamanio)
                        this.y=this.y+1;
                    this.pinta();
                }
                break;
            case 1: //abajo
                for(int i = 0; i<q; i++){
                    if(this.x<this.tamanio)
                        this.x=this.x+1;
                    this.pinta();
                }
                break;
            case 2: //izquierda
                for(int i = 0; i<q; i++){
                    if(this.y>0)
                        this.y=this.y-1;
                    this.pinta();
                }
                break;
            case 3: //arriba
                for(int i = 0; i<q; i++){
                    if(this.x>0)
                        this.x=this.x-1;
                    this.pinta();
                }
                break;
            default:{
                System.out.println(this.direccion + " No es una instrucción válida.");
            }
        }
        System.out.println("has avanzado "+q+" pasos en dirección: " + this.direccion + ", con pluma: " + this.pluma+". Estas en: "+this.x+","+this.y);
    }

    private void pinta(){
        if(this.pluma ==1)
            this.matriz[this.x][this.y]=1;
    }

    private void pluma(int ink){
        switch(ink){
            case 1: //arriba
                this.pluma = 0;
                break;
            case 2: //abajo
                this.pluma = 1;
                break;
            default:{
                System.out.println(ink + " No es una instrucción válida.");
            }
        }
    }

    private void joystick(int turn){
        switch(turn){
            case 3: //derecha
                this.direccion = this.direccion +1;
                if(this.direccion > 3)
                    this.direccion = 0;
                break;
            case 4: //izquierda
                this.direccion = this.direccion -1;
                if(this.direccion < 0)
                    this.direccion = 3;
                break;
            default:{
                System.out.println(turn + " No es una instrucción válida.");
            }
        }
    }

    private void imprimir(){
        IntStream.range(0,20).forEach(a->{
            System.out.println("");
            IntStream.range(0,20).forEach(b->{
                System.out.print("["+matriz[a][b]+"]");
            });
        });
    }

}
