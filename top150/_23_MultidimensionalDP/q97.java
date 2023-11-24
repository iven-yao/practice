package top150._23_MultidimensionalDP;

public class q97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        return dfs(s1,s2,s3,0,0,dp);
    }
    public boolean dfs(String s1,String s2,String s3,int i,int j,int[][] dp){
        if(i==s1.length() && j==s2.length())return true;
        if(dp[i][j]==1)return true;
        if(dp[i][j]==-1)return false;
        if(i<s1.length() && s1.charAt(i)==s3.charAt(i+j) && dfs(s1,s2,s3,i+1,j,dp)){
            dp[i][j]=1;
            return true;
        }
        else if(j<s2.length() && s2.charAt(j)==s3.charAt(i+j) && dfs(s1,s2,s3,i,j+1,dp)){
            dp[i][j]=1;
            return true;
        }
        else{
            dp[i][j]=-1;
            return false;
        }
    }
}
