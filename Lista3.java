
package lista3;

import java.util.ArrayList;


public class Lista3 {

    public static void main(String[] args) 
    {
        //10 arvores de ordem 2
        ArvoreB arvore_ordenada2[] = new ArvoreB[10];
        int tamanho;
        //criando as 10 arvoresB instanciando
        for(int i=0;i<10;i++)
        {
            arvore_ordenada2[i]=new ArvoreB(2);
        }
        //preechendo as 10 arvoresB
        for(int j=0;j<10;j++)
        {
           tamanho=10000*(j+1);
           for(int k=1;k<=tamanho;k++)
           {
               arvore_ordenada2[j].insere(k);
           }
        }
        //elemento não pertecente a arvore
        Item aux=new Item(1000000);
        System.out.println("ARVORE B ORDENADA DE ORDEM 2:");
        for(int i=0;i<10;i++)
        {
            arvore_ordenada2[i].pesquisa(aux);
            System.out.println("Arvore "+(i+1)+" paginas visitadas "+arvore_ordenada2[i].getPaginasVisitadas()+" comparações "+arvore_ordenada2[i].getNumerosComparacoes());
        }
        //10 arvores de ordem 4
        //
        ArvoreB arvore_ordenada4[] = new ArvoreB[10];
        //criando as 10 arvoresB instanciando
        for(int i=0;i<10;i++)
        {
            arvore_ordenada4[i]=new ArvoreB(4);
        }
        //preechendo as 10 arvoresB
        for(int j=0;j<10;j++)
        {
           tamanho=10000*(j+1);
           for(int k=1;k<=tamanho;k++)
           {
               arvore_ordenada4[j].insere(k);
           }
        }
        System.out.println("ARVORE B ORDENADA DE ORDEM 4:");
        for(int i=0;i<10;i++)
        {
            arvore_ordenada4[i].pesquisa(aux);
            System.out.println("Arvore "+(i+1)+" paginas visitadas "+arvore_ordenada4[i].getPaginasVisitadas()+" comparações "+arvore_ordenada4[i].getNumerosComparacoes());
        }
         //10 arvores de ordem 8
        //
        ArvoreB arvore_ordenada8[] = new ArvoreB[10];
        //criando as 10 arvoresB instanciando
        for(int i=0;i<10;i++)
        {
            arvore_ordenada8[i]=new ArvoreB(8);
        }
        //preechendo as 10 arvoresB
        for(int j=0;j<10;j++)
        {
           tamanho=10000*(j+1);
           for(int k=1;k<=tamanho;k++)
           {
               arvore_ordenada8[j].insere(k);
           }
        }
        System.out.println("ARVORE B ORDENADA DE ORDEM 8:");
        for(int i=0;i<10;i++)
        {
            arvore_ordenada8[i].pesquisa(aux);
            //mostrando na tela os resultados da pesquisa
            System.out.println("Arvore "+(i+1)+" paginas visitadas "+arvore_ordenada8[i].getPaginasVisitadas()+" comparações "+arvore_ordenada8[i].getNumerosComparacoes());
        }
         //10 arvores de ordem 16
        //
        ArvoreB arvore_ordenada16[] = new ArvoreB[10];
        //criando as 10 arvoresB instanciando
        for(int i=0;i<10;i++)
        {
            arvore_ordenada16[i]=new ArvoreB(16);
        }
        //preechendo as 10 arvoresB
        for(int j=0;j<10;j++)
        {
           tamanho=10000*(j+1);
           for(int k=1;k<=tamanho;k++)
           {
               arvore_ordenada16[j].insere(k);
           }
        }
        System.out.println("ARVORE B ORDENADA DE ORDEM 16:");
        for(int i=0;i<10;i++)
        {
            arvore_ordenada16[i].pesquisa(aux);
            System.out.println("Arvore "+(i+1)+" paginas visitadas "+arvore_ordenada16[i].getPaginasVisitadas()+" comparações "+arvore_ordenada16[i].getNumerosComparacoes());
        }
    }
    
}
