/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista3;

/**
 *
 * @author Aluno
 */
public class ArvoreB {

    private static class Pagina {

        // Numero de itens que a pagina contem atualmente
        int numeroCorrenteItens;
        // Vetor que armazena todos os itens da pagina
        long itensPagina[];
        // Vetor que indica quem sao as paginas filhas ( ele aponta para todas asfilhas )
        Pagina paginasFilhas[];

        // Metodo construtor da classe pagina
        public Pagina(int qtdemaximaRegistros) {
            this.numeroCorrenteItens = 0;
            this.itensPagina = new long[qtdemaximaRegistros];
            this.paginasFilhas = new Pagina[qtdemaximaRegistros + 1];
        }
    }
    
    private Pagina pagRaiz;
    private final int minimoRegistrosPagina, maximoRegistrosPagina;
    private boolean arvoreDesbalanceada;
    private long regRetorno;
    private int paginasVisitadas;
    private int numeroComparados;
    
    private void insereNaPagina(Pagina paginaAtual, long registro, Pagina filhaDireita) 
    {
        int k = paginaAtual.numeroCorrenteItens - 1;//variavel auxiliar,k recebe posição válida mais à direita
        while (k >= 0 && (registro - paginaAtual.itensPagina[k]) < 0)//desloca-se itenspagina e paginasfilhas para a direita até que a posição correta de inserção seja encontrada
        {
            paginaAtual.itensPagina[k + 1] = paginaAtual.itensPagina[k];
            paginaAtual.paginasFilhas[k + 2] = paginaAtual.paginasFilhas[k + 1];
            k--;
        }
        //registro e filha direita são inseridos na página atual
        paginaAtual.itensPagina[k + 1] = registro;
        paginaAtual.paginasFilhas[k + 2] = filhaDireita;
        paginaAtual.numeroCorrenteItens++;
    }
    //Construtot da classe ArvoreB
    public ArvoreB(int qtdeminimaRegistros) 
    {
        this.pagRaiz = null;
        this.minimoRegistrosPagina = qtdeminimaRegistros;
        this.maximoRegistrosPagina = 2 * qtdeminimaRegistros;
        this.arvoreDesbalanceada = false;
        this.regRetorno = -1;
        this.paginasVisitadas = 0;
        this.numeroComparados = 0;
    }

    private Pagina insere(int registro, Pagina paginaAtual) 
    {
        Pagina paginaRetorno = null;
        if (paginaAtual == null) //página onde o registro deve ser inserido foi encontrada
        {
            this.arvoreDesbalanceada = true;
            this.regRetorno = registro;
        } 
        else 
        {
            int i = 0;
            //a partir da esquerda, i é posicionado no primeiro elemento que seja maior ou igual ao registro a ser inserido
            while ((i < paginaAtual.numeroCorrenteItens - 1) && (registro - paginaAtual.itensPagina[i] > 0)) 
            {
                i++;
            }
            if (registro == paginaAtual.itensPagina[i])
            {
                System.out.println(" Erro : Registro ja existente ");// mensagem de erro, caso o elemento ja esteja contido na arvore
                this.arvoreDesbalanceada = false;
            }
            else 
            {
                if (registro - paginaAtual.itensPagina[i] > 0) //verifica se a próxima página a ser pesquisada deve ser a da direita ou a da esquerda.
                {
                    i++;
                }
                paginaRetorno = insere(registro, paginaAtual.paginasFilhas[i]);
                if (arvoreDesbalanceada) 
                {
                    if (paginaAtual.numeroCorrenteItens < this.maximoRegistrosPagina) //se página tem espaço e consequentemento insero o elemento
                    {
                        this.insereNaPagina(paginaAtual, regRetorno, paginaRetorno);
                        this.arvoreDesbalanceada = false;
                        paginaRetorno = paginaAtual;
                    } 
                    else 
                    {
                        Pagina apTemp = new Pagina(this.maximoRegistrosPagina);
                        apTemp.paginasFilhas[0] = null;
                        if (i <= this.minimoRegistrosPagina) //verifica se o novo registro ficará na nova pagina temporária ou na página atual
                        {
                           
                            this.insereNaPagina(apTemp, paginaAtual.itensPagina[this.maximoRegistrosPagina - 1], paginaAtual.paginasFilhas[this.maximoRegistrosPagina]);
                            paginaAtual.numeroCorrenteItens--;
                            this.insereNaPagina(paginaAtual, regRetorno, paginaRetorno);
                        } 
                        else 
                        {
                            this.insereNaPagina(apTemp, regRetorno, paginaRetorno);
                        }
                        for (int j = this.minimoRegistrosPagina + 1; j < this.maximoRegistrosPagina; j++) //transfere a metade direita da página atual para a nova página temporária
                        {
                            this.insereNaPagina(apTemp, paginaAtual.itensPagina[j], paginaAtual.paginasFilhas[j + 1]);
                            paginaAtual.paginasFilhas[j + 1] = null;
                        }
                        paginaAtual.numeroCorrenteItens = this.minimoRegistrosPagina;
                        apTemp.paginasFilhas[0] = paginaAtual.paginasFilhas[this.minimoRegistrosPagina + 1];//atribui o registro do meio à regRetorno, ou seja, vai subir na árvore
                        regRetorno = paginaAtual.itensPagina[this.minimoRegistrosPagina];
                        paginaRetorno = apTemp;
                    }
                }
            }
        }
        return (this.arvoreDesbalanceada ? paginaRetorno : paginaAtual);
    }
    //Pesquisa se um elemento esta contido na arvore
    private Item pesquisa(Item reg, Pagina ap) 
    {
        if (ap == null) 
        {
            return null;
        } 
        else 
        {
            int i = 0;
            this.paginasVisitadas++;
            while ((i < ap.numeroCorrenteItens - 1) && (reg.compara(ap.itensPagina[i]) > 0))  //enquanto a posição atual (i) é válida e o registro procurado é maior que o atual
            {
                i++;
                this.numeroComparados++;
            }
            if (reg.compara(ap.itensPagina[i]) == 0) //se o elemento for o igual contido na pagina retorno o elemento
            {
                this.numeroComparados++;
                Item aux = new Item(ap.itensPagina[i]);
                return aux;
            } 
            else if (reg.compara(ap.itensPagina[i]) < 0) //se elemento procurado for menor que a posição atual, chama-se recursivamente a busca na sub árvore esquerda
            {
                this.numeroComparados++;
                return pesquisa(reg, ap.paginasFilhas[i]);
            } 
            else //recursividade para o lado direito da arvore
            {
                return pesquisa(reg, ap.paginasFilhas[i + 1]);
            }
        }
    }
    //metodo publico para a chamada do metodo privado pesquisa
    public boolean pesquisa(Item reg) 
    {
        this.numeroComparados = 0;
        return this.pesquisa(reg, this.pagRaiz) != null;
    }
    
    public void insere(int reg) 
    {
        //inicializa o processo de inserção de uma chave à partir do nó raiz.
        Pagina temp1 = this.insere(reg, this.pagRaiz);//depois da inserção do elemento reg a partir de this.pagRaiz é verificado se houve crescimento ou não da raiz
        if (this.arvoreDesbalanceada)
        {
            Pagina temp2 = new Pagina(this.maximoRegistrosPagina);//uma nova página é criada e atribuída à raiz
            temp2.itensPagina[0] = this.regRetorno;
            temp2.paginasFilhas[0] = this.pagRaiz;
            temp2.paginasFilhas[1] = temp1;
            this.pagRaiz = temp2;
            this.pagRaiz.numeroCorrenteItens++;
        } 
        else 
        {
            this.pagRaiz = temp1;
        }
    }

    public int getPaginasVisitadas() //retorna o numero de paginas visitadas durante uma pesquisa de um elemento
    {
        return this.paginasVisitadas;
    }

    public int getNumerosComparacoes() //retorna o numero de comparações durante uma pesquisa de um elemento
    {
        return this.numeroComparados;
    }
    //metodo privado de impressao dos elementos contido na arvore
    private void imprime(Pagina p, int nivel)
    {
        if (p != null) 
        {
            System.out.print("  Nivel " + nivel + ":");
            for (int i = 0; i < p.numeroCorrenteItens; i++) 
            {
                System.out.print(" " + p.itensPagina[i]);
            }
            System.out.println();
            for (int i = 0; i <= p.numeroCorrenteItens; i++) 
            {
                if (p.paginasFilhas[i] != null) 
                {
                    if (i < p.numeroCorrenteItens) 
                    {
                        System.out.println("  Esq: " + p.itensPagina[i]);
                    } 
                    else 
                    {
                        System.out.println("  Dir: " + p.itensPagina[i - 1]);
                    }
                }
                imprime(p.paginasFilhas[i], nivel + 1);
            }
        }
    }
    //metodo publico para chamada do privado imprime
    public void imprime () 
    {
        System.out.println ("ARVORE:");
        this.imprime (this.pagRaiz, 0);
    }
  
    
}
