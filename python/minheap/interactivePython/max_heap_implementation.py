class MaxHeapError(Exception):
    pass


class Max_Heap():

    def __init__(self):
        self.heap_list = [0]
        self.current_size = 0

    def size(self):
        return self.current_size

    def __len__(self):
        return self.size()

    def __repr__(self):
        return str(self.heap_list)

    def is_empty(self):
        return self.size() == 0

    def perc_up(self, i):
        while i // 2 > 0:
            if self.heap_list[i] > self.heap_list[i // 2]:
                self.heap_list[i //
                               2], self.heap_list[i] = self.heap_list[i], self.heap_list[i // 2]
            i //= 2

    def insert(self, data):
        self.heap_list.append(data)
        self.current_size += 1
        self.perc_up(self.size())

    def max_child(self, node):
        if node * 2 + 1 > self.size():
            return node * 2
        if self.heap_list[node * 2] > self.heap_list[node * 2 + 1]:
            return node * 2
        return node * 2 + 1

    def perc_down(self, current):
        while current * 2 <= self.size():
            # get max_child
            max_child = self.max_child(current)
            if self.heap_list[current] < self.heap_list[max_child]:
                self.heap_list[current], self.heap_list[max_child] = self.heap_list[max_child], self.heap_list[current]
            current = max_child

    def delete_maximum(self):
        if self.size() == 0:
            raise MaxHeapError("Heap is empty")
        self.heap_list[1] = self.heap_list[self.size()]
        data = self.heap_list[1]
        self.heap_list.pop()
        self.current_size -= 1
        self.perc_down(1)
        return data


if __name__ == "__main__":
    heap = Max_Heap()
    heap.insert(10)
    print(heap)
    heap.insert(20)
    print(heap)
    heap.insert(30)
    print(heap)
    heap.insert(50)
    print(heap)
    heap.insert(40)
    print(heap)
    heap.insert(15)
    print(heap)
    heap.delete_maximum()
    print(heap)

"""
Output:
[0, 10]
[0, 20, 10]
[0, 30, 10, 20]
[0, 50, 30, 20, 10]
[0, 50, 40, 20, 10, 30]
[0, 50, 40, 20, 10, 30, 15]
[0, 40, 30, 20, 10, 15]
"""
