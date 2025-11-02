public class cntUnguard {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
       int[][] grid = new int[m][n];
       for(int[] wall:walls) grid[wall[0]][wall[1]] = 2;
       for(int[] guard:guards) grid[guard[0]][guard[1]]= 1;
       int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
       for(int[] guard:guards){
        int r = guard[0];
        int c = guard[1];
        for(int[] dir:dirs){
            int dr = dir[0];
            int dc = dir[1];
            int nr = r + dr;
            int nc = c + dc;
            while(nr >= 0 && nr < m && nc >= 0 && nc < n){
                if (grid[nr][nc] == 1 || grid[nr][nc] == 2) break;
            if (grid[nr][nc] == 0) grid[nr][nc] = 3;
            nr += dr;
            nc += dc;
            }
        }
    }
    int count = 0;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if (grid[i][j] == 0) count++;
        }
    }
    return count;
}

}
