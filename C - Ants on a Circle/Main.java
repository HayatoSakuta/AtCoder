import java.util.Scanner;
import java.util.Arrays;

public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        //入力を変数で受け取る
        int n = sc.nextInt(); //蟻の引数
        int l = sc.nextInt(); //円周の長さ
        int t = sc.nextInt(); //制限時間
        int[] x = new int[n]; //蟻の初期位置
        int[] w = new int[n]; //蟻の最初の移動方向
        for(int i=0; i<n; i++){
            x[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }



        //t秒後の蟻の座標を計算
        int[] position = new int[n];  //t秒後の蟻の座標を格納
        for(int i=0; i<n; i++){
            if(w[i]==1){
                position[i] = (x[i]+t) % l ;
            }else{
                position[i] = ( (x[i]-t) % l + l ) % l ;
            }
        }
        int position_ant_0 = position[0];



        //ant_0がt秒間で他の蟻とすれ違う回数を計算する
        int c = 0; //他の蟻とすれ違う総回数
        double time_first = 0; //二匹の蟻が最初にすれ違うまでの時間
        for(int i=1; i<n; i++){

            if(w[0]==w[i]){
                continue;
            }

            if(w[0]==1){
                time_first = (double)Math.abs(x[0]-x[i])/2;
            }else{
                time_first = (double)Math.abs(x[0]+l-x[i])/2;
            }
            
            c += (int)Math.floor( 1 + 2*(t-time_first)/l );
        }



        //ant_0のt秒後の番号を求める
        int num = 0; //t秒後のゼッケン
        if(w[0]==1){
            num = c%n ;
        }else{
            num = ( (-c)%n + n ) % n ;
        }



        //答えを格納する配列answerを定義
        int[] answer = new int[n];
        //後の計算のために配列positionを昇順ソートしておく
        Arrays.sort(position);



        //answer[num]に格納される値が入ったpositionのインデックスを求める（※positionに同じ値が入る可能性は一旦無視）
        int base = 0;
        for(int i=0; i<n; i++){
            if(position[i]==position_ant_0){
                base = i;
                if(position[(i+1)%n]==position[i]){
                    if(w[0]==2){
                        break;
                    }
                }
            }
        }

        if(base-num>=0){
            int z = base - num;
            for(int i=0; i<n; i++){
                answer[i] = position[z%n];
                z++ ;
            }
        }else{
            int z = num - base;
            for(int i=0; i<n; i++){
                answer[z%n] = position[i];
                z++ ;
            }
        }
        
        for(int i=0; i<n; i++){
            System.out.println(answer[i]);
        }

    }

}