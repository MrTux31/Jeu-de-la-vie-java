package com.jeudelavie;
import java.io.*;
/**
 * Classe contenant les fonctions permettant de générer un ou des jeux de la vie
 * et de les sauvegarder dans un fichier HTML
 * @author BESNIER, IUS, ROMA
 * 
 */
public class Main

{
    /**
     * Génère du code HTML pour afficher une matrice (avec des propriété CSS)
     * @author BESNIER Maxime
     * @param pfJeu IN : Le jeu de la vie
     * @return La chaîne de caractère contenant le code HTML
     */
    public static String toHTML(JeuDeLaVie pfJeu) throws Exception{
        // Déclaration des variables
        String chaine;
        String ln;
        
        // Ajout ligne par ligne de la matrice
        ln = System.getProperty("line.separator"); // Retour-chariot
        chaine = "<table border=\"1\"><caption>G&eacute;n&eacute;ration " + pfJeu.generation + "</caption>" + ln; // Permet d'afficher la génération ainsi que son numéro       
        for (int i = 1; i < ProgrammeMatriceEntier.getNbLignes(pfJeu.grille)-1; i++) {
            chaine += "<tr>"; 
            for (int j = 1; j < ProgrammeMatriceEntier.getNbColonnes(pfJeu.grille)-1; j++) {
                /* Pour de l'affichage, on attribut dans le code HTML une classe pour chaque case :
                    - white si la case est morte (0)
                    - black si la case est vivante (1)
                */
                if (ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) != 0) {
                    chaine += "<td class=\"black\">" + ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) + "</td>"; // La case est vivante
                } else {
                    chaine += "<td class=\"white\">" + ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) + "</td>"; // La case est morte
                }
            }
            chaine += "</tr>" + ln; // Fermeture de la ligne
        }
        chaine += "</table>" +ln; // Fermeture du tableau
        
        // Retour de la valeur
        return chaine;
    }
    /**
     * Sauvegarde le code HTML dans un fichier nommé index.html
     * @author BESNIER Maxime
     * @param pfChaine IN : Le code HTML
     */
    public static void saveHTML(String pfChaine) {
        String css = "<style>"; // Le Style CSS des tableaux
        css += "table";
        css += "{";
        css += "border-collapse:collapse;";
        css += "border: 2pt solid black;";
        css += "display: inline-table;";
        css += "text-align: center;";
        css += "margin: 10px;";
        css += "}";
        css += "tr,td { border: 1px solid black; height:20px;";
        css += "overflow:hidden;";
        css += "width:2vmin;";
        css += "height:2vmin;";
        css += "}";
        css += ".on { background-color:grey; }";
        css += ".white { color:white; }"; // Appliquage du style pour les cases mortes
        css += ".black { background-color:black; }"; // Appliquage du style pour les cases vivantes
        css += "</style>";
    
        String debutPage = "<html><head>" ; // Début de page HTML
        debutPage += "<title>TP: Jeu de la vie</title>" ;
        debutPage += "<meta http-equiv='Content-Type' content='application/xhtml+xml; charset=UTF-8' />" ;
        debutPage += css ;
        debutPage += "</head><body>" ;
    
        String finPage = "</body></html>" ; // Fin de page HTML
        
        // Sauvegarde dans le fichier index.html
        try {
            PrintStream out = new PrintStream("index.html");
            out.print(debutPage);
            out.println(pfChaine);
            out.print(finPage);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Permet de savoir si toutes les cellules d'un jeu de la vie sont mortes (matrice remplie de 0)
     * @author ROMA Quentin
     * @param pfJeu IN : Le jeu de la vie
     * @return true si toutes les cellules sont mortes, false sinon
     */
    public static boolean estVide(JeuDeLaVie pfJeu) throws Exception{
        // Déclaration des variables
        boolean vide = true;
        
        // Parcours de la matrice
        for (int i = 1; i <= pfJeu.nbL; i++) {
            for (int j = 1; j <= pfJeu.nbC; j++) {
                if (ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) != 0) { // La matrice n'est pas vide
                    vide = false;
                }
            }
        }
        
        // Retour de la valeur
        return vide;
    }
    /**Permet d'enregistrer la grille d'un jeu de la vie dans son historique (pour conserver les générations)
     * @author ROMA Quentin
     * @param pfJeu IN/OUT : le jeu de la vie
     */
    public static void saveHistorique(JeuDeLaVie pfJeu) throws Exception{
        //On vérifie qu'il reste de la place dans le tableau de l'historique
        if (pfJeu.historique.nbElt == pfJeu.historique.Elements.length){
            //Lancer une nouvelle exception
            throw new Exception("Impossible d'ajouter la génération dans l'historique, celui-ci est plein");
        }
        else{
            //Conversion de la grille du jeu de la vie en chaine de caractères.
            String conversionStringJeu = ProgrammeMatriceEntier.toString(pfJeu.grille);
            //On ajoute la chaine de caractère de la grille dans l'historique (copie en String de pfJeu.grille) pour conserver la génération
            pfJeu.historique.Elements[pfJeu.historique.nbElt] = conversionStringJeu;
            //Mettre à jour le nombre d'éléments du tableau de l'historique du jeu de la vie
            pfJeu.historique.nbElt += 1;
        }
    }
    /**Permet de vérifier si la grille d'un jeu de la vie est déjà présente dans l'historique (génération actuelle déjà dans l'historique)
     * ou bien vérifier que la grille contient uniquement des zéros
     * @author ROMA Quentin
     * @param pfJeu IN : le jeu de la vie contenant la génération à vérifier
     * @return true si dans historique (dans ce cas la génération est terminée)
     * false sinon
     */
    public static boolean grilleConnue(JeuDeLaVie pfJeu) throws Exception{
        boolean connu = false;
        //Pour chaque grille dans le tableau d'historique, on regarde si la grille de pfJeu (génération actuelle) est identique à pfJeu.historique.Elements[i]
        //Si les grilles sont identiques, cela signifie que la generation de pfJeu est déjà stockée dans l'historique
        for(int i=0; i<pfJeu.historique.nbElt;i++){
            //Appel de la fonction pour comparer deux grilles entre elles (on veux savoir si elles sont identiques)
            if(estIdentiqueGrille(pfJeu.historique.Elements[i],ProgrammeMatriceEntier.toString(pfJeu.grille)) || estVide(pfJeu)){
                //Si les grilles sont identiques (la grille de pfJeu est dans l'historique) ou la grille de pfJeu est vide, connu devient true
                connu = true;
            }
        }
        //On return false si la grille de pfJeu n'est pas présente dans l'historique ou bien qu'elle n'est pas vide
        return connu; 
    }
    /**Permet de vérifier si deux grilles (en string) sont identiques 
     * @author ROMA Quentin
     * @param pfGrille1 IN : la première grille
     * @param pfGrille2 IN : la deuxième grille
     * @return true si les deux grilles sont identiques, false sinon
     */
    public static boolean estIdentiqueGrille(String pfGrille1,String pfGrille2){
       boolean identique = true;
       //On compare les deux chaines de caractères entre elles pour savoir si elles sont différentes
       if(!pfGrille1.equals(pfGrille2)){
           //Les deux grilles sont différentes sont différentes alors identique est donc false
           identique = false;
       }
       //Si les deux grilles sont identiques on retournera alors true
       
       //On retourne identique
       return identique;
    }
    /**
     * Génère la prochaine génération de la matrice
     * @author IUS Adam
     * @param pfJeu IN / OUT : Le jeu
     */
    public static void Generer(JeuDeLaVie pfJeu) throws Exception{
        JeuDeLaVie nouveauJeu = new JeuDeLaVie(pfJeu.nbL, pfJeu.nbC);
        int somme;
        // Clone pfJeu dans nouveauJeu
        for (int i = 1; i <= pfJeu.nbL; i++) {
            for (int j = 1; j <= pfJeu.nbC; j++) {
                ProgrammeMatriceEntier.setElement(nouveauJeu.grille, i, j, ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j));
            } 
        }
        
        for (int i = 1; i <= pfJeu.nbL; i++) {
            for (int j = 1; j <= pfJeu.nbC; j++) {
                somme = 0;
                // Compter le nombre de cellule voisine vivante
                //parcours des 8 cellules autour
                for(int k = i-1; k <= i+1; k++){
                    for(int x = j-1; x <= j+1; x++){ 
                        if (k!=i || x != j){
                            somme += ProgrammeMatriceEntier.getElement(pfJeu.grille,k,x);
                            
                        }
                    }
                } 
                
                // Si il y'a exactement 3 cellule voisine vivante et que la cellule actuelle est morte
                if (somme == 3 && (ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) == 0)){
                   ProgrammeMatriceEntier.setElement(nouveauJeu.grille, i, j, 1); //la cellue prends vie
                }
                // Si il n'y a pas 2 ou 3 cellule voisine vivante et que la cellule actuelle est vivante
                if (somme != 2 && somme != 3 && (ProgrammeMatriceEntier.getElement(pfJeu.grille, i, j) == 1)){
                   ProgrammeMatriceEntier.setElement(nouveauJeu.grille, i, j, 0); //la cellule meurt
                }
            } 
        }
        
        // Clone nouveauJeu dans pfJeu
        for (int i = 1; i <= pfJeu.nbL; i++) {
            for (int j = 1; j <= pfJeu.nbC; j++) {
                ProgrammeMatriceEntier.setElement(pfJeu.grille, i, j, ProgrammeMatriceEntier.getElement(nouveauJeu.grille, i, j));
            } 
        }
    }
    /**
     * Permet de jouer un jeu de la vie
     * @author BESNIER Maxime
     * @param pfJeu IN / OUT : Le jeu
     * @param pfHistorique IN / OUT : L'historique
     * @return La chaîne de caractères à écrire dans le fichier HTML
     */
    public static String jouer(JeuDeLaVie pfJeu) throws Exception{
        // Déclaration des variables
        boolean running;
        String allGen;
        
        // Algo
        allGen = "";
        running = true;
        while (pfJeu.generation < 10 && running) { // On s'arrête si running est false ou s'il y a plus de 10 générations
            allGen += toHTML(pfJeu); // On ajoute le code HTML (grilles) aux autres
            pfJeu.generation++;
            if (grilleConnue(pfJeu)) { // La matrice est vide ou est déjà connue
                running = false;
            } else {
                saveHistorique(pfJeu); // On sauvegarde dans l'hitorique
                Generer(pfJeu); // On génère la prochaine matrice
            }
        }
        // Retour de la valeur
        return allGen + "<br>"; // Retour-chariot en HTML (pour un meilleur rendu)
    }
    /**
     * Permet de mettre des cases de la MatriceEntier à 1 sans se préoccuper de la bordure
     * @autor BESNIER Maxime
     * @param pfJeu IN / OUT : Le jeu de la vie
     * @param pfI IN : La ligne
     * @param pfJ IN : La colonne
     */
    public static void setElementVivant(JeuDeLaVie pfJeu, int pfI, int pfJ) throws Exception{
        ProgrammeMatriceEntier.setElement(pfJeu.grille, pfI + 1, pfJ + 1, 1); // Permet de mettre des cases à 1 sans considérer la bordure
    }
    /**
     * Le main
     * @author BESNIER Maxime
     */
    public static void main(String[] args) {
        try {
            // Déclaration des variables
            boolean running;
            String allGen; // code HTML pour les générations
            
            JeuDeLaVie jeu1;
            JeuDeLaVie jeu2;
            JeuDeLaVie jeu3;
            JeuDeLaVie jeu4;
            JeuDeLaVie creeper;
            JeuDeLaVie steve;
            JeuDeLaVie amongUs;
            
            // Initialisation des variables
            allGen = "";
            
            jeu1  = new JeuDeLaVie(3, 3);
            jeu2  = new JeuDeLaVie(5, 5);
            jeu3  = new JeuDeLaVie(15, 9);
            jeu4  = new JeuDeLaVie(3, 3);
            creeper  = new JeuDeLaVie(8, 8);
            steve  = new JeuDeLaVie(8, 8);
            amongUs  = new JeuDeLaVie(10, 10);
            
            // Définition du Among Us
            setElementVivant(amongUs, 0, 2);
            setElementVivant(amongUs, 0, 3);
            setElementVivant(amongUs, 0, 4);
            setElementVivant(amongUs, 0, 5);
            setElementVivant(amongUs, 0, 6);
            setElementVivant(amongUs, 1, 1);
            setElementVivant(amongUs, 1, 6);
            setElementVivant(amongUs, 1, 7);
            setElementVivant(amongUs, 2, 0);
            setElementVivant(amongUs, 2, 1);
            setElementVivant(amongUs, 2, 2);
            setElementVivant(amongUs, 2, 3);
            setElementVivant(amongUs, 2, 4);
            setElementVivant(amongUs, 2, 7);
            setElementVivant(amongUs, 2, 8);
            setElementVivant(amongUs, 2, 9);
            setElementVivant(amongUs, 3, 0);
            setElementVivant(amongUs, 3, 4);
            setElementVivant(amongUs, 3, 7);
            setElementVivant(amongUs, 3, 9);
            setElementVivant(amongUs, 4, 0);
            setElementVivant(amongUs, 4, 4);
            setElementVivant(amongUs, 4, 7);
            setElementVivant(amongUs, 4, 9);
            setElementVivant(amongUs, 5, 0);
            setElementVivant(amongUs, 5, 1);
            setElementVivant(amongUs, 5, 2);
            setElementVivant(amongUs, 5, 3);
            setElementVivant(amongUs, 5, 4);
            setElementVivant(amongUs, 5, 7);
            setElementVivant(amongUs, 5, 9);
            setElementVivant(amongUs, 6, 1);
            setElementVivant(amongUs, 6, 7);
            setElementVivant(amongUs, 6, 8);
            setElementVivant(amongUs, 6, 9);
            setElementVivant(amongUs, 7, 1);
            setElementVivant(amongUs, 7, 3);
            setElementVivant(amongUs, 7, 4);
            setElementVivant(amongUs, 7, 7);
            setElementVivant(amongUs, 8, 1);
            setElementVivant(amongUs, 8, 4);
            setElementVivant(amongUs, 8, 7);
            setElementVivant(amongUs, 9, 1);
            setElementVivant(amongUs, 9, 2);
            setElementVivant(amongUs, 9, 3);
            setElementVivant(amongUs, 9, 4);
            setElementVivant(amongUs, 9, 5);
            setElementVivant(amongUs, 9, 6);
            setElementVivant(amongUs, 9, 7);
            
            // Définition du Steve
            for (int i = 0; i < 8; i++) {
                setElementVivant(steve, 0, i);
                setElementVivant(steve, 1, i);
            }
            setElementVivant(steve, 2, 0);
            setElementVivant(steve, 2, 7);
            setElementVivant(steve, 4, 2);
            setElementVivant(steve, 4, 5);
            setElementVivant(steve, 5, 3);
            setElementVivant(steve, 5, 4);
            setElementVivant(steve, 6, 2);
            setElementVivant(steve, 6, 5);
            setElementVivant(steve, 7, 2);
            setElementVivant(steve, 7, 3);
            setElementVivant(steve, 7, 4);
            setElementVivant(steve, 7, 5);
            
            // Définition du Creeper
            setElementVivant(creeper, 2, 1);
            setElementVivant(creeper, 2, 2);
            setElementVivant(creeper, 2, 5);
            setElementVivant(creeper, 2, 6);
            setElementVivant(creeper, 3, 1);
            setElementVivant(creeper, 3, 2);
            setElementVivant(creeper, 3, 5);
            setElementVivant(creeper, 3, 6);
            setElementVivant(creeper, 4, 3);
            setElementVivant(creeper, 4, 4);
            setElementVivant(creeper, 5, 2);
            setElementVivant(creeper, 5, 3);
            setElementVivant(creeper, 5, 4);
            setElementVivant(creeper, 5, 5);
            setElementVivant(creeper, 6, 2);
            setElementVivant(creeper, 6, 3);
            setElementVivant(creeper, 6, 4);
            setElementVivant(creeper, 6, 5);
            setElementVivant(creeper, 7, 2);
            setElementVivant(creeper, 7, 5);
            
            // Définition des jeux d'essai
            setElementVivant(jeu4, 0, 0);
            setElementVivant(jeu4, 0, 1);
            setElementVivant(jeu4, 1, 1);
            setElementVivant(jeu4, 1, 0);
            
            setElementVivant(jeu1, 0, 0);
            setElementVivant(jeu1, 1, 1);
            setElementVivant(jeu1, 2, 2);
            
            setElementVivant(jeu2, 1, 1);
            setElementVivant(jeu2, 1, 2);
            setElementVivant(jeu2, 1, 3);
            setElementVivant(jeu2, 2, 0);
            setElementVivant(jeu2, 2, 1);
            setElementVivant(jeu2, 2, 2);
            
            // Définition du bonhomme
            setElementVivant(jeu3, 1, 2);
            setElementVivant(jeu3, 1, 3);
            setElementVivant(jeu3, 1, 4);
            setElementVivant(jeu3, 1, 5);
            setElementVivant(jeu3, 1, 6);
            setElementVivant(jeu3, 2, 2);
            setElementVivant(jeu3, 2, 6);
            setElementVivant(jeu3, 3, 2);
            setElementVivant(jeu3, 3, 6);
            setElementVivant(jeu3, 4, 2);
            setElementVivant(jeu3, 4, 6);
            setElementVivant(jeu3, 5, 2);
            setElementVivant(jeu3, 5, 3);
            setElementVivant(jeu3, 5, 5);
            setElementVivant(jeu3, 5, 6);
            setElementVivant(jeu3, 6, 4);
            setElementVivant(jeu3, 7, 4);
            setElementVivant(jeu3, 8, 1);
            setElementVivant(jeu3, 8, 2);
            setElementVivant(jeu3, 8, 3);
            setElementVivant(jeu3, 8, 4);
            setElementVivant(jeu3, 8, 5);
            setElementVivant(jeu3, 8, 6);
            setElementVivant(jeu3, 8, 7);
            setElementVivant(jeu3, 9, 1);
            setElementVivant(jeu3, 9, 4);
            setElementVivant(jeu3, 9, 7);
            setElementVivant(jeu3, 10, 1);
            setElementVivant(jeu3, 10, 4);
            setElementVivant(jeu3, 10, 7);
            setElementVivant(jeu3, 11, 3);
            setElementVivant(jeu3, 11, 5);
            setElementVivant(jeu3, 12, 3);
            setElementVivant(jeu3, 12, 5);
            setElementVivant(jeu3, 13, 3);
            setElementVivant(jeu3, 13, 5);
            setElementVivant(jeu3, 14, 2);
            setElementVivant(jeu3, 14, 3);
            setElementVivant(jeu3, 14, 5);
            setElementVivant(jeu3, 14, 6);
            
            
            // Jeux d'essai
            allGen += jouer(jeu1); 
            allGen += jouer(jeu2);
            allGen += jouer(jeu4);
            
            // Bonhomme
            allGen += jouer(jeu3);
            
            // Memes
            allGen += jouer(creeper);
            allGen += jouer(steve);
            allGen += jouer(amongUs);
            
            saveHTML(allGen); // Appliquage du style CSS et sauvegarde dans un ficher index.html
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} // Fin de classe

