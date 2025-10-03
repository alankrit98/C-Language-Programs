class Solution {
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] sw : swaps) {
            graph.get(sw[0]).add(sw[1]);
            graph.get(sw[1]).add(sw[0]);
        }
        boolean[] visited = new boolean[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> indices = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int u = q.poll();
                    indices.add(u);
                    for (int v : graph.get(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            q.add(v);
                        }
                    }
                }
                List<Integer> values = new ArrayList<>();
                for (int idx : indices) values.add(nums[idx]);
                Collections.sort(values, Collections.reverseOrder());
                List<Integer> evenIdx = new ArrayList<>();
                List<Integer> oddIdx = new ArrayList<>();
                for (int idx : indices) {
                    if (idx % 2 == 0) evenIdx.add(idx);
                    else oddIdx.add(idx);
                }
                int eCount = evenIdx.size();
                int oCount = oddIdx.size();
                for (int j = 0; j < values.size(); j++) {
                    if (j < eCount) ans += values.get(j);
                    else ans -= values.get(j);
                }
            }
        }
        return ans;
    }
}
