package com.gcc.ccc;

public class IslandChecker {
    private static final char LAND = 'L';
    private static final char WATER = 'W';
    private int rows, cols;
    private char[][] map;
    private boolean[][] visited;

    public IslandChecker(char[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
        this.visited = new boolean[rows][cols];
    }

    public boolean areOnSameIsland(int[] coord1, int[] coord2) {
        if(coord1[0] == coord2[0] && coord1[1] == coord2[1])
            return true;
        dfs(coord1[0], coord1[1]);
        return visited[coord2[1]][coord2[0]];
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || map[y][x] == WATER || visited[y][x]) {
            return;
        }

        visited[y][x] = true;

        dfs(x + 1, y);  // Down
        dfs(x - 1, y);  // Up
        dfs(x, y + 1);  // Right
        dfs(x, y - 1);  // Left
    }
}
