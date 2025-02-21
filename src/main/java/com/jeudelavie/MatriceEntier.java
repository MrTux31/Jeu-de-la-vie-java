package com.jeudelavie;
/**
 * Matrice d'entiers
 *
 * @author ROMA Quentin
 */
public class MatriceEntier
{
    int nbL; 
    int nbC; 
    int tabMat[][]; 
    
    MatriceEntier(int pfNbLignes, int pfNbColonnes) throws Exception {
        if (pfNbLignes <= 0 || pfNbColonnes <= 0) {
            throw new Exception ("Erreur : Nombre de lignes ou de colonnes doit être > 0");
        }
        this.nbL = pfNbLignes; 
        this.nbC = pfNbColonnes; 
        this.tabMat = new int[this.nbL][this.nbC]; 
        for (int i = 0; i < this.nbL; i++) {
            for (int j = 0; j < this.nbC; j++) {
                this.tabMat[i][j] = 0; 
            }
        }
    }
}

