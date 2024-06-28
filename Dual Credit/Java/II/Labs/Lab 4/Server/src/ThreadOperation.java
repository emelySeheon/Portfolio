//Although I was not supposed to use threads in Lab 3, Proffessor Jong said it was okay for me to use them this time.

public class ThreadOperation extends Thread{
    private int[][] ASubmatrix = new int[1][1];
    private int[][] BSubmatrix;
    public int[][] CSubmatrix = new int[ASubmatrix.length][ASubmatrix[0].length];

    public ThreadOperation(int[][] AMatrix, int[][] BMatrix) {
        ASubmatrix = new int[AMatrix.length][AMatrix[0].length];
        BSubmatrix = new int[BMatrix.length][BMatrix[0].length];
        CSubmatrix = new int[BMatrix.length][BMatrix[0].length];

        for(int i = 0; i<AMatrix.length;++i){
            for(int j = 0; j<AMatrix[i].length;++j){
                ASubmatrix[i][j] = AMatrix[i][j];
            }
        }

        for(int i = 0; i<BMatrix.length;++i){
            for(int j = 0; j<BMatrix[i].length;++j){
                BSubmatrix[i][j] = BMatrix[i][j];
            }
        }
    }
    @Override
    public void run() {
        for (int i = 0; i<BSubmatrix.length;++i){
            for (int j = 0; j<BSubmatrix[i].length;++j){
                CSubmatrix[i][j]= ASubmatrix[i][j]+BSubmatrix[i][j];
            }
        }
    }
}