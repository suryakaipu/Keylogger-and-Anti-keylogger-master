import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class HillCipher
{
    public String decryptMessage(String msg, String key)
    {
        msg = msg.replace(" ", "");
        int[][] keyMatrix = createKeyMatrix(key);
        if (msg.length() % 2 != 0)
        {
            msg += "0";
        }
        int[][] msgMatrix = createMsgMatrix(msg);
        int[][] keyMatrixInverted = invertMatrix(keyMatrix);
        String decryptedMsg = multiplyMatrices(keyMatrixInverted, msgMatrix);
        return decryptedMsg;
    }
    public int[][] invertMatrix(int[][] matrix)
    {
        int a = matrix[0][0];
        int b = matrix[0][1];
        int c = matrix[1][0];
        int d = matrix[1][1];
        int x = 1 / ((a*d) - (b*c));
        matrix[0][0] = x * d;
        matrix[0][1] = x * -b;
        matrix[1][0] = x * -c;
        matrix[1][1] = x * a;
        return matrix;
    }
    public String encryptMessage(String msg, String key)
    {
        String encrptedMsg = "";
        //remove spaces
        msg = msg.replace(" ", "");
        int[][] keyMatrix = createKeyMatrix(key);
        //check length and append 0's if not even to the msg
        if (msg.length() % 2 != 0)
        {
            msg += "0";
        }
        //encrypt msg into multiple 2x1 matrices
        int[][] msgMatrix = createMsgMatrix(msg);
        //hill cipher algo => kp % 26 where k is key matrix, p is a 2x1 matrix in msg matrix
        //we do modulo 26 to get back the original chars
        encrptedMsg = multiplyMatrices(keyMatrix, msgMatrix);
        return encrptedMsg;
    }
    public String multiplyMatrices(int[][] keyMatrix, int[][] msgMatrix)
    {
        String ans = "";
        int n = msgMatrix.length;
        for (int i = 0; i < n; i++)
        {
            ans += (char) ((keyMatrix[0][0] * msgMatrix[i][0]) + (keyMatrix[0][1] *
                    msgMatrix[i][1])) % 26;
            ans += (char) ((keyMatrix[1][0] * msgMatrix[i][0]) + (keyMatrix[1][1] *
                    msgMatrix[i][1])) % 26;
        }
        return ans;
    }
    public int[][] createMsgMatrix(String msg)
    {
        msg = msg.toUpperCase();
        int l = msg.length();
        int n = l / 2;
        int pointer = 0;
        int[][] msgMatrix = new int[n][2];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                msgMatrix[i][j] = (int) msg.charAt(pointer) - 65;
            }
        }
        return msgMatrix;
    }
    public int[][] createKeyMatrix(String key)
    {
        key = key.toUpperCase();
        //check if key is perfect square
        int l = key.length();
        //dimensions of key matrix
        int m = 2;
        if(l != 4)
        {
            System.out.println("Please enter a valid key whose length is 4");
            return null;
        }
        int[][] keyMatrix = new int[m][m];
        int pointer = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < m; j++)
            {
                keyMatrix[i][j] = (int) key.charAt(pointer) - 65;
                pointer++;
            }
        }
        return keyMatrix;
    }
}