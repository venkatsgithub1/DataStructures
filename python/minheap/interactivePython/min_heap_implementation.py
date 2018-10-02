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
        parent_index = i // 2
        while parent_index > 0:
            if self.heap_list[i] < self.heap_list[parent_index]:
                self.heap_list[i], self.heap_list[parent_index] = self.heap_list[parent_index], self.heap_list[i]
            parent_index //= 2

    def insert(self, data):
        if(self.current_size == len(self.heap_list)):
            raise BinHeapError("Heap is full")
        self.heap_list.append(data)
        self.current_size += 1
        self.perc_up(self.current_size)

    def perc_down(self, i):
        parent_index = i * 2
        # loop till i * 2 is less than size
        while parent_index <= self.current_size:
            # get min child
            min_child = self.min_child(i)
            # if element at i is greater than at min child
            if self.heap_list[i] > self.heap_list[min_child]:
                # swap i and min child
                self.heap_list[i], self.heap_list[min_child] = self.heap_list[min_child], self.heap_list[i]
            # go to next child to percolate down
            parent_index = min_child

    def del_minimum(self):
        if self.is_empty():
            raise BinHeapError("Heap is empty")
        return_val = self.heap_list[0]
        # place last to first
        self.heap_list[1] = self.heap_list[self.current_size]
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
