
public class Determinan_Kofaktor{
    
    static float determinan_kofaktor(float[][] m){
        if(m.length == 0){
            return 0;
        }
        if(m.length == 1){
            return m[0][0];
        }
        if(m[0].length == 2){
            return (m[0][0] * m[1][1]) - (m[0][1] *m[1][0]);
        }

        float result = 0;
        int l;
        for(int i = 0; i < m.length; i++){
            float[][] temp = new float[m.length-1][m[i].length-1];
            for(int j = 0; j < temp.length ; j++){
                l = 0;
                for(int k = 0; k < m[i].length;k++){
                    if(k != i){
                        temp[j][l] = m[j+1][k];
                        l++;
                    }
                }

            }
            result += m[0][i] * Math.pow(-1, 2+i)* determinan_kofaktor(temp);
        }

        return result;
    }

    public static void main(String[] args){
        float[][] matriks = {{21,12,3,0},{81,52,13,42},{3,2,51,42},{11,23,53,53}};
        System.out.println(determinan_kofaktor(matriks));
    }
}