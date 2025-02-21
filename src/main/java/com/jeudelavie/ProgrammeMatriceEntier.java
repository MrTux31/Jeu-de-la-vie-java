
package com.jeudelavie;
/**
 * Classe contenant les fonctions permettant de manipuler une matrice d'entiers 
 * 
 */
public class ProgrammeMatriceEntier
{
    /**Retourne le nombre de lignes de pfMat de type MatriceEntier
     * @param MatriceEntier pfMat : La matrice
     * @return le nombre de lignes de pfMat
     */
    public static int getNbLignes(MatriceEntier pfMat) {
        return pfMat.nbL; 
    }
    
    /**Retourne le nombre de colonnes de pfMat de type MatriceEntier
     * @param MatriceEntier pfMat : La matrice
     * @return le nombre de colonnes de pfMat
     */
    public static int getNbColonnes(MatriceEntier pfMat) {
        return pfMat.nbC; 
    }
    
    /**Retourne l'élement de pfMat de type MatriceEntier placé dans la matrice
     * en lignes et colonnes indiquées 
     * @param MatriceEntier pfMat : La matrice
     * @param int pfLigne : la ligne de l'élement 
     * @param int pfColonne : la colonne de l'élement
     * @return l'élement de ligne et colonne indiqué
     */
    public static int getElement(MatriceEntier pfMat, int pfLigne, 
    int pfColonne) throws Exception {
        if (pfLigne < 0 || pfLigne >= getNbLignes(pfMat) || 
        pfColonne < 0 || pfColonne >= getNbColonnes(pfMat)) {
            throw new Exception ("Erreur : Numéro de ligne ou de colonne invalide"); 
        }
        
        return pfMat.tabMat[pfLigne][pfColonne];
    }
    
    /**Retourne la somme des lignes de pfMat de type MatriceEntier
     *@param MatriceEntier pfMat : La matrice
     *@param int pfLigne : La ligne dont on veut la somme des éléments 
     *@return la somme des éléments de la pfLigne 
     */
    public static int somLigne(MatriceEntier pfMat, int pfLigne) throws Exception {
        if (pfLigne < 0 || pfLigne >= getNbLignes(pfMat)) {
            throw new Exception ("Erruer : Numéro de ligne (pfLigne) invalide");
        }
        
        int somme = 0; 
        for (int i = 0; i < getNbColonnes(pfMat); i++) {
            somme += getElement(pfMat, pfLigne, i); 
        }
        return somme; 
    }
    
    /**Retourne la somme des colonnes de pfMat de type MatriceEntier
     *@param MatriceEntier pfMat : La matrice
     *@param int pfColonne : La colonne dont on veut la somme des éléments 
     *@return la somme des éléments de la pfColonne 
     */
    public static int somColonne(MatriceEntier pfMat, int pfColonne) throws Exception {
        if (pfColonne < 0 || pfColonne >= getNbColonnes(pfMat)) {
            throw new Exception ("Erreur : Numéro de colonne (pfColonne) invalide");
        }
        
        int somme = 0; 
        for (int i = 0; i < getNbLignes(pfMat); i++) {
            somme += getElement(pfMat, i, pfColonne); 
        }
        return somme; 
    }
    
    /**Retourne un booléen True si pfMatrice de type MatriceEntier est carrée et False si
     * elle ne l'est pas
     * @param MatriceEntier pfMat : La matrice
     * @return si pfMat est carrée (true) ou non (false)  
     */
    public static boolean estCarree(MatriceEntier pfMat) {
       boolean carree = false; 
       if (getNbLignes(pfMat) == getNbColonnes(pfMat)) {
            carree = true; 
       }
       return carree; 
    }
    
    /**Retourne un booléen True si pfMatrice de type MatriceEntier est diagonale et False si
     * elle ne l'est pas
     * @param MatriceEntier pfMat : La matrice
     * @return si pfMat est diagonale (true) ou non (false)  
     */
    public static boolean estDiagonale(MatriceEntier pfMat) throws Exception {
        if (!estCarree(pfMat)) {
            throw new Exception ("Erreur : La matrice (pfMat) n'est pas carrée");
        }
        boolean diagonale = true; 
        int i = 0; 
        int j; 
        while (i < getNbLignes(pfMat) && diagonale) {
            j = 0; 
            while (j < getNbColonnes(pfMat) && diagonale) {
                if (getElement(pfMat, i, j) != 0 && i != j) {
                    diagonale = false; 
                }
                j++; 
            }
            i++;
        }
        return diagonale; 
    }
    
    /**Insére une valeur dans pfMat dans la ligne pfLigne et la colonne pfColonne avec 
     * pfElement comme valeur indiquée 
     * @param MatriceEntier pfMat : La matrice
     * @param int pfLigne : La ligne 
     * @param int pfColonne : La colonne 
     * @param int pfElement : L'élement à insérer
     */
    public static void setElement(MatriceEntier pfMat, int pfLigne, int pfColonne, int 
    pfElement) throws Exception {
        if (pfLigne < 0 || pfLigne >= getNbLignes(pfMat) || 
        pfColonne < 0 || pfColonne >= getNbColonnes(pfMat)) {
            throw new Exception ("Erreur : Numéro de ligne ou de colonne invalide (set)");
        }
        
        pfMat.tabMat[pfLigne][pfColonne] = pfElement; 
    }
    
    /**Insére les valeurs dans pfMat dans la première diagonale avec pfElement comme 
     * valeur indiquée 
     * @param MatriceEntier pfMat : La matrice 
     * @param int pfElement : L'élement à insérer
     */
    public static void setPremiereDiagonale(MatriceEntier pfMat, int pfElement)
    throws Exception {
        if (!estCarree(pfMat)) {
            throw new Exception ("Erreur : La matrice (pfMat) n'est pas carrée (setP.Diago.");
        }
        
        int i = 0; 
        int j;
        int nbL = getNbLignes(pfMat); 
        int nbC = getNbColonnes(pfMat);  
        while (i < nbL) {
            j = 0; 
            while (j < nbC) {
                if (i == j) {
                    setElement(pfMat, i, j, pfElement); 
                }
                j++; 
            }
            i++;
        }
    }
    
    /**Insére les valeurs dans pfMat dans la seconde diagonale avec pfElement comme 
     * valeur indiquée 
     * @param MatriceEntier pfMat : La matrice 
     * @param int pfElement : L'élement à insérer
     */
    public static void setSecondeDiagonale(MatriceEntier pfMat, int pfElement)
    throws Exception {
        if (!estCarree(pfMat)) {
            throw new Exception ("Erreur : La matrice (pfMat) n'est pas carrée (setS.Diago.");
        }
 
        int nbL = getNbLignes(pfMat); 
        int nbC = getNbColonnes(pfMat);
        int i = 0; 
        int j = nbC - 1;
        while (i < nbL) {
            setElement(pfMat, i, j, pfElement); 
            j--;
            i++;
        }
    }
    
    /**Multiplie pfMat par un nombre pfValeur 
     *@param MatriceEntier pfMat : La matrice
     *@param int pfValeur : La valeur à multiplié la matrice par
     */
    public static void mulMatNombre(MatriceEntier pfMat, int pfValeur) {
        int nbL = getNbLignes(pfMat);
        int nbC = getNbColonnes(pfMat); 
        for (int i = 0; i < nbL; i++) {
            for (int j = 0; j < nbC; j++) {
                pfMat.tabMat[i][j] *= pfValeur;  
            } 
        }
    }
    
    /**Retourne pfMat sous une chaine de charactère
     * @param MatriceEntier pfMat : La matrice
     * @return La matrice en chaine de charactère
     */
    public static String toString(MatriceEntier pfMat) {
        int nbL = getNbLignes(pfMat);
        int nbC = getNbColonnes(pfMat); 
        String matrice = ""; 
        String ln = System.getProperty("line.separator") ;
        for (int i = 0; i < nbL; i++) {
            for (int j = 0; j < nbC; j++) {
                matrice += pfMat.tabMat[i][j] + " "; 
            }
            matrice += ln; 
        }
        return matrice; 
    }
    
    /**Retourne pfMat sous une chaine de charactère en HTML 
     * @param MatriceEntier pfMat : La matrice
     * @return La matrice en chaine de charactère en HTML 
     */
    public static String toHTML(MatriceEntier pfMat) {
        int nbL = getNbLignes(pfMat);
        int nbC = getNbColonnes(pfMat);
        String ln = System.getProperty("line.separator") ;
        String chaine = "<table border=\"1\">"; 
        chaine += ln; 
        for (int i = 0; i < nbL; i++) {
            chaine += "<tr>"; 
            for (int j = 0; j < nbC; j++) {
                chaine += "<td>" + pfMat.tabMat[i][j] + "</td>"; 
            }
            chaine += "</tr>"; 
            chaine += ln; 
        }
        chaine += "</table>"; 
        chaine += ln;
        return chaine; 
    }
    
    public static void main() {
        try {
            MatriceEntier maMatrice = new MatriceEntier(5, 5);
            setElement(maMatrice, 2, 3, 12);
            setPremiereDiagonale(maMatrice, 7);
            setSecondeDiagonale(maMatrice, 8);
            mulMatNombre(maMatrice, 4); 
            System.out.println(getNbLignes(maMatrice)); 
            System.out.println(getElement(maMatrice, 2, 3));
            System.out.println(somLigne(maMatrice, 2)); 
            System.out.println(somColonne(maMatrice, 3));
            System.out.println(estCarree(maMatrice));
            System.out.println(estDiagonale(maMatrice));
            System.out.println(toString(maMatrice)); 
            System.out.println(toHTML(maMatrice)); 
        } catch (Exception e) {System.out.println(e) ;}
    }
}

