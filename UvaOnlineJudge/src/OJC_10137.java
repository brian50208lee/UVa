import java.util.Arrays;

/**
 * 10137 The Trip: �ƧǪk (�`�N�̫��X�ɤ��n���H 100 �D�p��, �|�ɭP��T�פ����T)
 * ���絲�G: �q�L 0.320ms
 * ������: 2008-09-23
 *
 * @author Raymond Wu (�p���Y)
 */
public class OJC_10137 {

        // ��J�w�İ� (�w�ĪŶ��ݭn�̷��D�ؽվ�)
        public static byte[] cinbuf = new byte[1024];

        // Ū���@�ӳ�r (�^��m�W�]�t�ťծɽФ��n��)
        public static String readToken() {
                int offset = 0;
                int bytedata = -1;
               
                try {
                        // ���L�D��r���r�� '\t','\n','\r',' '
                        bytedata = System.in.read();
                        while(bytedata==9||bytedata==10||bytedata==13||bytedata==32) {
                                bytedata = System.in.read();
                        }

                        // ���J��r���r��
                        while(bytedata!=-1) {
                                if(bytedata==9||bytedata==10||bytedata==13||bytedata==32) {
                                        break;
                                } else {
                                        cinbuf[offset++] = (byte)bytedata;
                                }
                                bytedata = System.in.read();
                        }
                } catch(Exception e) {}
               
                if(offset+bytedata==-1) return null; // ��y����
                return new String(cinbuf,0,offset);
        }
       
        // �ন int ���A (�� Integer.parseInt() ��)
        public static int parseInt(String s) {
                int i;
                int mul = 10;
                int value = s.charAt(s.length()-1)-48;
               
                for(i=s.length()-2;i>=0;i--) {
                        value += (s.charAt(i)-48)*mul;
                        mul *= 10;
                }
               
                return value;
        }
       
        // �ন int ���A (�� Integer.parseInt() ��)
        public static int parseCent(String s) {
                int cent;
                int dotpos = s.indexOf(".");
               
                if(dotpos!=-1) {
                        cent = parseInt(s.substring(0,dotpos))*100+parseInt(s.substring(dotpos+1));
                } else {
                        cent = parseInt(s)*100;
                }
               
                return cent;
        }
       
        public static void main(String[] args) {
                int i;
                int n = parseInt(readToken());
                int total;
                int average;
                int d1,d2,change;
                int remind;
                int more_idx, less_idx;
                int[] cost = new int[1000];

                while(n>0) {
                        total = 0;
                        for(i=0;i<n;i++) {
                                String token = readToken();
                                cost[i] = parseCent(token);
                                total += cost[i];
                        }
                        remind  = total%n;
                        average = (total-remind)/n;
                       
                        Arrays.sort(cost,0,n);
                        less_idx = 0;
                        more_idx = n-1;
                        total = 0;
                       
                        while(less_idx<more_idx) {
                                d1 = average-cost[less_idx];
                                d2 = cost[more_idx]-average;
                                if(remind>0) d2--;
                                change = Math.min(d1,d2);
                                total += change;
                                cost[less_idx] += change;
                                cost[more_idx] -= change;
                                if(d1==change) less_idx++;
                                if(d2==change) {
                                        more_idx--;
                                        if(remind>0) remind--;
                                }
                        }
                       
                        if(remind>1) total = total+remind-1;
                        System.out.printf("$%d.%02d\n",total/100,total%100);
                        n = parseInt(readToken());
                }
        }

}
