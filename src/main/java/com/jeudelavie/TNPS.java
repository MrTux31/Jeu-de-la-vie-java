package com.jeudelavie;
/**
 * Enregistrement TNPS
 * Tableau non plein de String
 *
 * @author ROMA Quentin
 */
public class TNPS
{
    String Elements[];
    int nbElt;
    /**Constructeur sans paramètres de TNPS
     * @author ROMA Quentin
     * Initialise un tableau à une dimension de String (10 éléments max)
     * 
     */
    public TNPS(){
        //Créer un tableau à une entrée de 10 String
        this.Elements = new String[10];
        //Initialiser le nombre d'éléments du à 0
        this.nbElt = 0;
    }
}

