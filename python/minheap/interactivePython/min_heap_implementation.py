class BinHeapError(Exception):
    pass


class BinHeap:
    def __init__(self):
        self.heap_list = [0]
        self.current_size = 0

    def is_empty(self):
        return self.current_size == 0

    def __len__(self):
        return self.current_size

    def __repr__(self):
        return str(self.heap_list)

    def perc_up(self, i):
        while i // 2 > 0:
            if self.heap_list[i] < self.heap_list[i // 2]:
                self.heap_list[i], self.heap_list[i //
                                                  2] = self.heap_list[i // 2], self.heap_list[i]
            i //= 2

    def insert(self, data):
        self.heap_list.append(data)
        self.current_size += 1
        self.perc_up(self.current_size)

    def perc_down(self, i):
        # loop till i * 2 is less than size
        while i * 2 <= self.current_size:
            # get min child
            min_child = self.min_child(i)
            # if element at i is greater than at min child
            if self.heap_list[i] > self.heap_list[min_child]:
                # swap i and min child
                self.heap_list[i], self.heap_list[min_child] = self.heap_list[min_child], self.heap_list[i]
            # go to next child to percolate down
            i = min_child

    def del_minimum(self):
        if self.is_empty():
            raise BinHeapError("Heap is empty")
        return_val = self.heap_list[0]
        # place last to first
        self.heap_list[1] = self.heap_list[self.current_size]
        self.heap_list.pop()
        # decrement heap size
        self.current_size -= 1
        # percolate down
        self.perc_down(1)
        # return min
        return return_val

    def min_child(self, i):
        if i * 2 + 1 > self.current_size:
            return i * 2

        if self.heap_list[i * 2] < self.heap_list[i * 2 + 1]:
            return i * 2
        return i * 2 + 1


if __name__ == "__main__":
    heap = BinHeap()
    heap.insert(10)
    heap.insert(20)
    heap.insert(30)
    heap.insert(50)
    heap.insert(40)
    heap.insert(15)
    print(heap)
    heap.del_minimum()
    print(heap)

"""
Output:
[0, 10, 20, 15, 50, 40, 30]
[0, 15, 20, 30, 50, 40]
"""
