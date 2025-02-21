package com.jeudelavie;
/**
 * Enregistrement JeuDeLaVie
 *
 * @author ROMA Quentin
 */
public class JeuDeLaVie
{
    //le nombre de lignes, le nombre de colonnes et le numéro de génération du jeu de la vie
    int nbL, nbC, generation;
    
    //Matrice contenant la génération actuelle
    MatriceEntier grille;
    
    // Le tableau non plein de String dans lequel on met l'historique des générations
    TNPS historique;
    /**
     * Constructeur paramétrique du jeu de la vie
     * @param pfNbl IN : le nombre de lignes
     * @param pfNbC IN : le nombre de colonnes
     */
    public JeuDeLaVie(int pfNbL, int pfNbC) throws Exception {
        this.nbL = pfNbL;
        this.nbC = pfNbC;
        this.generation = 0;
        this.grille = new MatriceEntier(pfNbL + 2,pfNbC + 2);
        this.historique = new TNPS();
    }
}

