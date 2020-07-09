
public class Principal {

  public static void main(String[] args) {

    // Define a solução
    Algoritmo.setSolucao("000001010100");
    // Define os caracteres existentes
    Algoritmo.setCaracteres("01");
    // taxa de crossover de 60%
    Algoritmo.setTaxaDeCrossover(0.5);
    // taxa de mutação de 30%
    Algoritmo.setTaxaDeMutacao(0.3);
    // elitismo
    boolean eltismo = true;
    // tamanho da população
    int tamPop = 100;
    // numero máximo de gerações
    int numMaxGeracoes = 10000;

    // define o número de genes do indivíduo baseado na solução
    int numGenes = Algoritmo.getSolucao().length();

    // cria a primeira população aleatérioa
    Populacao populacao = new Populacao(numGenes, tamPop);

    boolean temSolucao = false;
    int geracao = 0;

    int solutionFitness = new Individuo(Algoritmo.getSolucao(), false).getAptidao();
    System.out.println("Iniciando... Aptidão da solução: " + solutionFitness);

    // loop até o critério de parada
    while (!temSolucao && geracao < numMaxGeracoes) {
      geracao++;

      // cria nova populacao
      populacao = Algoritmo.novaGeracao(populacao, eltismo);

      System.out.println("Geração " + geracao + " | Aptidão: " + populacao.getIndividuo(0).getAptidao() + " | Melhor: \t"
          + printDirections(populacao.getIndividuo(0).getGenes()));

      // verifica se tem a solucao
      temSolucao = populacao.temSolucao(Algoritmo.getSolucao());
    }

    if (geracao == numMaxGeracoes) {
      System.out.println("Número Maximo de Gerações | " + populacao.getIndividuo(0).getGenes() + " "
          + populacao.getIndividuo(0).getAptidao());
    }

    if (temSolucao) {
      System.out.println("Encontrado resultado na geração " + geracao + " | " + populacao.getIndividuo(0).getGenes()
          + " (Aptidão: " + populacao.getIndividuo(0).getAptidao() + ")");
    }
  }

  static String printDirections(String genes) {
    String output = "";

    String[] geneBits = genes.split("(?<=\\G.{2})");

    for (int i = 0; i < geneBits.length; i++) {
      if (geneBits[i].compareTo("00") == 0) output += "-> ";
      if (geneBits[i].compareTo("01") == 0) output += "/\\ ";
      if (geneBits[i].compareTo("10") == 0) output += "<- ";
      if (geneBits[i].compareTo("11") == 0) output += "\\/ ";
    }

    return output;
  }
}
