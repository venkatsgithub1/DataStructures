class Vertex:
    def __init__(self, key):
        self.id = key
        self.connected_to = {}

    def add_neighbour(self, nbr, weight=0):
        self.connected_to[nbr] = weight

    def __str__(self):
        return str(self.id) + 'connected to: ' + str([x.id for x in self.connected_to])

    def get_connections(self):
        return self.connected_to.keys()

    def get_id(self):
        return self.id

    def get_weight(self, nbr):
        return self.connected_to[nbr]


class Graph:
    def __init__(self):
        self.vertices_dict = {}
        self.number_of_vertices = 0

    def add_vertex(self, key):
        self.vertices_dict[key] = Vertex(key)
        self.number_of_vertices += 1

    def get_vertex(self, n):
        if n in self.vertices_dict:
            return self.vertices_dict[n]
        return None

    def __contains__(self, n):
        return n in self.vertices_dict

    def add_edge(self, from_vertex, to_vertex, cost=0):
        if from_vertex not in self.vertices_dict:
            self.vertices_dict[from_vertex] = Vertex(from_vertex)
        if to_vertex not in self.vertices_dict:
            self.vertices_dict[to_vertex] = Vertex(to_vertex)
        self.vertices_dict[from_vertex].add_neighbour(
            self.vertices_dict[to_vertex], cost)

    def get_vertices(self):
        return self.vertices_dict.keys()

    def __iter__(self):
        return iter(self.vertices_dict.values())


if __name__ == "__main__":
    graph = Graph()
    for a in range(6):
        graph.add_vertex(a)

    print(graph.vertices_dict)

    graph.add_edge(0, 1, 5)
    graph.add_edge(0, 5, 2)
    graph.add_edge(1, 2, 4)
    graph.add_edge(2, 3, 9)
    graph.add_edge(3, 4, 7)
    graph.add_edge(3, 5, 3)
    graph.add_edge(4, 0, 1)
    graph.add_edge(5, 4, 8)
    graph.add_edge(5, 2, 1)

    for vertex in graph:
        for neighbour in vertex.get_connections():
            print("vertex={} neighbour={} weight={}".format(
                vertex.get_id(), neighbour.get_id(), vertex.get_weight(neighbour)))
