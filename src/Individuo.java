import java.util.Arrays;
import java.util.Random;

public class Individuo {

    static int WALL = -50;
    static int WIN = 100;


    private String genes = "";
    private int aptidao = 0;

    //gera um indivíduo aleatório
    public Individuo(int numGenes) {
        genes = "";
        Random r = new Random();
        
        String caracteres = Algoritmo.getCaracteres();
        
        for (int i = 0; i < numGenes; i++) {
            genes += caracteres.charAt(r.nextInt(caracteres.length()));
        }
        
        geraAptidao();        
    }

    //cria um indivíduo com os genes definidos
    public Individuo(String genes) {    
        this.genes = genes;
        
        Random r = new Random();
        //se for mutar, cria um gene aleatório
        if (r.nextDouble() <= Algoritmo.getTaxaDeMutacao()) {
            String caracteres = Algoritmo.getCaracteres();
            String geneNovo="";
            int posAleatoria = r.nextInt(genes.length());
            for (int i = 0; i < genes.length(); i++) {
                if (i==posAleatoria){
                    geneNovo += caracteres.charAt(r.nextInt(caracteres.length()));
                }else{
                    geneNovo += genes.charAt(i);
                }
                
            }
            this.genes = geneNovo;   
        }
        geraAptidao();
    }

    //cria um indivíduo com os genes definidos e força mutação (ou não mutação)
    public Individuo(String genes, Boolean shouldMutate) {    
      this.genes = genes;
      
      Random r = new Random();
      //se for mutar, cria um gene aleatório
      if (shouldMutate) {
          String caracteres = Algoritmo.getCaracteres();
          String geneNovo="";
          int posAleatoria = r.nextInt(genes.length());
          for (int i = 0; i < genes.length(); i++) {
              if (i==posAleatoria){
                  geneNovo += caracteres.charAt(r.nextInt(caracteres.length()));
              }else{
                  geneNovo += genes.charAt(i);
              }
              
          }
          this.genes = geneNovo;   
      }
      geraAptidao();
  }

    //gera o valor de aptidão, será calculada pelo número de bits do gene iguais ao da solução
    private void geraAptidao() {
        String solucao = Algoritmo.getSolucao();
        Maze maze = new Maze();

        String[] solucaoBits = solucao.split("(?<=\\G.{2})");
        String[] geneBits = genes.split("(?<=\\G.{2})");
        String[] directions = new String[geneBits.length];

        for (int i = 0; i < geneBits.length; i++) {
          if (geneBits[i].compareTo("00") == 0) directions[i] = Maze.RIGHT;
          if (geneBits[i].compareTo("01") == 0) directions[i] = Maze.UP;
          if (geneBits[i].compareTo("10") == 0) directions[i] = Maze.LEFT;
          if (geneBits[i].compareTo("11") == 0) directions[i] = Maze.DOWN;
        }

        Maze.Cell currentCell = maze.cells[0][0];
        int currentX = 0;
        int currentY = 0;

        // muro ou vitória?
        for (int i=0; i<directions.length; i++) {
          // se nao pode ir, deu de cara em um muro
          if ( !Arrays.asList(currentCell.actions).contains(directions[i]) ){
            aptidao += WALL;
          } else {
            if (directions[i].compareTo(Maze.UP) == 0 && currentY + 1 <= 3) currentY += 1; 
            if (directions[i].compareTo(Maze.DOWN) == 0 && currentY - 1 >= 0) currentY -= 1; 
            if (directions[i].compareTo(Maze.RIGHT) == 0 && currentX + 1 <= 3) currentX += 1; 
            if (directions[i].compareTo(Maze.LEFT) == 0 && currentX - 1 >= 0) currentX -= 1; 

            currentCell = maze.cells[currentY][currentX];
            if (currentCell.exit) aptidao += WIN;
          }
        }

        // está na direção certa?
        for (int i = 0; i < geneBits.length; i++) {
          if (geneBits[i].compareTo(solucaoBits[i]) == 0) {
            aptidao += 5;
          }
        }

        // está minimamente parecido?
        for (int i = 0; i < solucao.length(); i++) {
          if (solucao.charAt(i) == genes.charAt(i)) {
              aptidao++;
          }
        }
    }

    public int getAptidao() {
        return aptidao;
    }

    public String getGenes() {
        return genes;
    }
}