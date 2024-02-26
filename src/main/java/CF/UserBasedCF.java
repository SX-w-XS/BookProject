package CF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBasedCF {
    public Map<String, Map<String, Double>> userItemMatrix; //，用来存放每个用户对每个物品的评分值。

    public UserBasedCF(Map<String, Map<String, Double>> userItemMatrix){
        this.userItemMatrix = userItemMatrix;
    } //构造函数接收用户-物品评分矩阵

    //获取用户之间的相似度矩阵
    private Map<String, Map<String, Double>> getUserSimilarityMatrix(){
        Map<String, Map<String, Double>> userSimilarityMatrix = new HashMap<>();
        for(String user1 : userItemMatrix.keySet()){
            Map<String, Double> similarityMap = new HashMap<>();
            for(String user2 : userItemMatrix.keySet()){
                if(user1.equals(user2)){
                    similarityMap.put(user2, 1.0);
                }else{
                    double similarity = getUserSimilarity(user1, user2);
                    similarityMap.put(user2, similarity);
                }
            }
            userSimilarityMatrix.put(user1, similarityMap);
        }
        return userSimilarityMatrix;
    }

    //获取两个用户之间的相似度
    private double getUserSimilarity(String user1, String user2){
        Map<String, Double> itemMap1 = userItemMatrix.get(user1);
        Map<String, Double> itemMap2 = userItemMatrix.get(user2);
        List<String> commonItems = new ArrayList<>();
        for(String item : itemMap1.keySet()){
            if(itemMap2.containsKey(item)){
                commonItems.add(item);
            }
        }
        if(commonItems.isEmpty()){
            return 0.0;
        }
        double distance = 0.0;
        for(String item : commonItems){
            double diff = itemMap1.get(item) - itemMap2.get(item);
            distance += Math.pow(diff, 2);
        }

        return 1 / (1 + Math.sqrt(distance));
    }

    //获取用户的推荐清单
    public Map<String, Double> getUserRecommendations(String user){
        Map<String, Double> itemScoreMap = new HashMap<>();
        Map<String, Double> userSimilarityMap = getUserSimilarityMatrix().get(user);
        for(String item : userItemMatrix.get(user).keySet()){

            double itemScore = 0.0;
            double similaritySum = 0.0;
            for(String otherUser : userItemMatrix.keySet()){
                if(!user.equals(otherUser) && userItemMatrix.get(otherUser).containsKey(item)){
                    double similarity = userSimilarityMap.get(user);
                    itemScore += similarity * userItemMatrix.get(otherUser).get(item);
                    similaritySum += similarity;


                }
            }
            itemScoreMap.put(item, itemScore / similaritySum);

        }
        return itemScoreMap;
    }
}