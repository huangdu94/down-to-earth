from _ast import List


class Solution:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        if source == destination:
            return True
        edge_map = {}
        for i in range(n):
            edge_map[i] = []
        for edge in edges:
            x = edge[0]
            y = edge[1]
            edge_map[x].append(y)
            edge_map[y].append(x)
        queue = [source]
        visited = set()
        while queue:
            for i in range(len(queue)):
                cur = queue.pop(0)
                for next in edge_map[cur]:
                    if next == destination:
                        return True
                    if next not in visited:
                        queue.append(next)
                        visited.add(cur)
        return False
