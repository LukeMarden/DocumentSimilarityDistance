import java.util.Arrays;
public class Main {

    public static void main(String[] args) {
//        for (int i = 0;i<6;i++) {
//            CourseworkUtilities initialise = new CourseworkUtilities();
//            String[] Q = initialise.generateDictionary(10,10);
//            String[] S = initialise.generateDocument(Q,100);
//            int featureVector[] = calculateFeatureVector(Q, S);
//        } //used for time tests


//        int[] numDocss = {2,10,50,100}, dicwords = {1,10,50,100}, docwords = {1,10,50,100,200};
//        for (int z = 0; z<4;z++) {
//            for (int y = 0; y<5; y++) {
//                for (int x =0;x<5;x++) {
//                    int numDocs = numDocss[3];
//                    CourseworkUtilities initialise = new CourseworkUtilities();
//                    String[] Q = initialise.generateDictionary(dicwords[z], 7);
//                    String[][] S = new String[numDocs][];
//
//                    for (int i=0;i<numDocs;i++) {
//                        S[i] = initialise.generateDocument(Q,docwords[y]);
//                    }
//                    int[][] featureVector = new int [S.length][];
//                    for (int i = 0;i<S.length;i++){
//                        featureVector[i] = calculateFeatureVector(Q, S[i]);
//                    }
//                    int[] closestMatch = calculateClosestMatch(Q,S);
//                }
//                System.out.print("\n");
//            }
//
//            System.out.println("\n");
//        } //used for time tests


        int numDocs = 10;
        CourseworkUtilities initialise = new CourseworkUtilities();
        String[] Q = initialise.generateDictionary(10, 7);
        String[][] S = new String[numDocs][];

        for (int i=0;i<numDocs;i++) {
            S[i] = initialise.generateDocument(Q,100); //initialises the amount of docs that are required
        }
        int[][] featureVector = new int [S.length][]; //this holds all the feature vectors for each combination
        for (int i = 0;i<S.length;i++){
            featureVector[i] = calculateFeatureVector(Q, S[i]); //calculates each possibility of feature vector
        }
        int[] closestMatch = calculateClosestMatch(Q,S); //this is holds the closest doc for each index of document. calling the method calculates

    }


    public static int[] calculateFeatureVector(String[] Q, String[] A) {
//        long timeBefore = System.nanoTime();
        int[] featureVector = new int[Q.length];
        for (int i =0;i<A.length;i++) {
            for (int j=0;j<Q.length;j++) {
                if (A[i] == Q[j]) {
                    featureVector[j]++; //this iterates through each dictionary word and compares it to each word in a document
                }
            }
        }
//        long timeAfter = System.nanoTime() - timeBefore;
//        System.out.println(timeAfter);
        return featureVector;
    }

    public static int calculateDocumentSimilarityDistance(int[] S0, int[] S1) {
//        long timeBefore = System.nanoTime();
        int DSD = 0;
        for (int i = 0; i<S0.length;i++) {
            if (S0[i]>=S1[i]) {
                DSD = DSD +(S0[i] - S1[i]); //this calculates the difference in
            }
            else {
                DSD = DSD +(S1[i] - S0[i]);
            }
        }
//        long timeAfter = System.nanoTime() - timeBefore;
//        System.out.println(timeAfter);
        return DSD;
    }
    public static int[] calculateClosestMatch(String[] Q, String[][] S) {
//        long timeBefore = System.nanoTime();
        int[] ClosestMatches = new int[S.length];
        for (int i =0;i<S.length;i++) {
            int DSD[] = new int[S.length];
            int xFVector[] = calculateFeatureVector(S[i], Q); //this stores the feature vector of the document thats being compared to
            int position = 0;
            int min = 1000;

            for (int j =0;j<S.length;j++){
                int[] SFVector = calculateFeatureVector(S[j], Q); //this calculates the feature vector of the document to compare to xFVector
                DSD[j] = calculateDocumentSimilarityDistance(xFVector, SFVector); //this compares the two feature vectors
                System.out.println(DSD[j]); //this prints out the distance between them
                if (DSD[j] == 0) {
                    continue; //if DSD = 0 than it is comparing the original document to itself
                }
                else if( min > DSD[j]) {
                    min = DSD[j]; //this stores the smallest distance
                    position=j; //this stores the position of the smallest
                }
            }
            System.out.println("Closest Match Is S[" + position + "]"); //documents begin from 0
            ClosestMatches[i] = position; //this stores the position of each of the closest matches

        }
//        long timeAfter = System.nanoTime() - timeBefore;
//        System.out.print(timeAfter + ",");
        return ClosestMatches;




    }
}
