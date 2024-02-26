package CF;

import dao.impl.UserDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserBasedCF {
    public  List<Integer> getRecommendSort(String userName){

        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        UserDaoImpl dao = new UserDaoImpl();
        list = dao.getRecommend(userName);
        System.out.println("list为"+list);
        list1 = dao.getAllRecommend(null);
        Map<String, Map<String, Double>> userItemMatrix = new HashMap<>();
        Map<String, Double> user1Items = new HashMap<>();
        user1Items.put("0", Double.valueOf(list.get(0)));
        user1Items.put("1", Double.valueOf(list.get(1)));
        user1Items.put("2", Double.valueOf(list.get(2)));
        user1Items.put("3", Double.valueOf(list.get(3)));
        user1Items.put("4", Double.valueOf(list.get(4)));
        user1Items.put("5", Double.valueOf(list.get(5)));
        userItemMatrix.put("user1", user1Items);
        Map<String, Double> user2Items = new HashMap<>();
        user2Items.put("0", Double.valueOf(list1.get(0)));
        user2Items.put("1", Double.valueOf(list1.get(1)));
        user2Items.put("2", Double.valueOf(list1.get(2)));
        user2Items.put("3", Double.valueOf(list1.get(3)));
        user2Items.put("4", Double.valueOf(list1.get(4)));
        user2Items.put("5", Double.valueOf(list1.get(5)));
        userItemMatrix.put("user2", user2Items);

        UserBasedCF cf = new UserBasedCF(userItemMatrix);
        System.out.println(cf.userItemMatrix);
        Map<String, Double> recommendations = cf.getUserRecommendations("user1");
        System.out.println(recommendations);
        Double[] a=new Double[6];
        for(int i=0;i<=5;i++){
            a[i]=0.0;
        }
        Map<String,Double> mp=new HashMap<>();
        mp=recommendations;
        int i=0;
        for(String key: mp.keySet()){

            Double value=mp.get(key);
            a[i]=value;
            i+=1;
        }
        Double max2=-1.0;
        Double max1=-1.0;
        int Imax1=0;
        int Imax2=0;

        for(int j=0;j<=5;j++){
            if(a[j]>max1) {
                Imax1=j;
                max1=a[j];
            }
        }
        for(int j=0;j<=5;j++){
            if(a[j]>max2&&Imax1!=j){
                Imax2=j;
                max2=a[j];
            }
        }
        System.out.println(Imax1);
        System.out.println(Imax2);
        List<Integer> sort=new ArrayList<>();
        sort.add(Imax1);
        sort.add(Imax2);
        return sort;
    }

    public static void main(String[] args) {
             TestUserBasedCF t=new TestUserBasedCF();
             t.getRecommendSort("xinchen");
}
}