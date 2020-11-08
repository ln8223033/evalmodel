package com.ln.evalmodel.ahp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuN
 * @Date 2020/10/12 0023 23:26
 * @Version 1.0
 * 一致性验证修正
 */
public class AHPConsistency {
    public Map<String, Integer> AHPConsistency(double[][] matrix) {
        int N = matrix[0].length;

        double sum[] = new double[N];
        for (int i = 0; i < N; i++) {
            sum[i] = 0.0;
            for (int j = 0; j < N; j++) {
                sum[i] += (matrix[i][N-1] * matrix[N-1][j]);
            }

        }
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                matrix[k][s] = matrix[k][s] / sum[s];
            }
        }
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                matrix[m][n] = matrix[m][n] / matrix[m][0];
            }
        }

        double max=0;
        int row = 0;
        int train = 0;
        for (int x = 0;x < N; x++) {
            for (int y = 0; y < N; y++) {
                if ((Math.abs(matrix[x][y]-1))>max){
                    max = (Math.abs(matrix[x][y]-1));
                    row=x;
                    train=y;
                }
            }
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("row",row);
        map.put("train",train);
        return map;
    }
}
