class Node:
    def __init__(self, value=None):
        self.value = value
        self.left_child = None
        self.right_child = None
        self.parent = None


class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, value):
        if self.root is None:
            self.root = Node(value)
        else:
            self._insert(value, self.root)

    def _insert(self, value, current_node):
        if value < current_node.value:
            # go left.
            if current_node.left_child is None:
                current_node.left_child = Node(value)
                current_node.left_child.parent = current_node
            else:
                self._insert(value, current_node.left_child)
        elif value > current_node.value:
            # go right.
            if current_node.right_child is None:
                current_node.right_child = Node(value)
                current_node.right_child.parent = current_node
            else:
                self._insert(value, current_node.right_child)
        else:
            print("value already in tree!")

    def print_tree(self):
        if self.root is not None:
            self._print_tree(self.root)

    def _print_tree(self, current_node):
        if current_node is not None:
            self._print_tree(current_node.left_child)
            print(current_node.value)
            self._print_tree(current_node.right_child)

    def tree_height(self):
        if self.root is not None:
            return self._tree_height(self.root, 0)
        else:
            return 0

    def _tree_height(self, current_node, current_height):
        if current_node is None:
            return current_height
        left_height = self._tree_height(current_node.left_child, current_height + 1)
        right_height = self._tree_height(current_node.right_child, current_height + 1)
        return max(left_height, right_height)

    def search(self, value):
        if self.root is not None:
            return self._search(value, self.root)
        return False

    def _search(self, value, current_node):
        if current_node is not None:
            if value < current_node.value:
                # go left
                return self._search(value, current_node.left_child)
            elif value > current_node.value:
                # go right.
                return self._search(value, current_node.right_child)
            else:
                # value = current node's value.
                return True
        return False

    def find(self, value):
        if self.root is not None:
            return self._find(value, self.root)
        else:
            return None

    def _find(self, value, current_node):
        if current_node is not None:
            if value == current_node.value:
                return current_node
            if value < current_node.value:
                # go left
                return self._find(value, current_node.left_child)
            if value > current_node.value:
                # go right
                return self._find(value, current_node.right_child)
        return False

    def delete_value(self, value):
        return self.delete_node(self.find(value))

    def delete_node(self, node):

        def min_value_node(n):
            current = n
            while current.left_child is not None:
                current = current.left_child
            return current

        def num_children(n):
            children_count = 0
            if n.left_child is not None:
                children_count += 1
            if n.right_child is not None:
                children_count += 1
            return children_count

        node_parent = node.parent

        node_children = num_children(node)

        # CASE 1: if child count  = 0
        if node_children == 0:
            if node_parent.left_child == node:
                node_parent.left_child = None
            if node_parent.right_child == node:
                node_parent.right_child = None

        # CASE 2: if child count = 1
        if node_children == 1:
            if node.left_child is not None:
                child = node.left_child
            else:
                child = node.right_child

            # replace node to be deleted with child node.
            if node_parent.left_child == node:
                node_parent.left_child = child
            if node_parent.right_child == node:
                node_parent.right_child = child

            child.parent = node_parent

        # CASE 3: if child count = 2
        if node_children == 2:
            # get in-order successor.
            successor = min_value_node(node.right_child)
            node.value = successor.value
            self.delete_node(successor)


def fill_tree(search_tree, num_elements=100, max_int=1000):
    from random import randint
    for _ in range(num_elements):
        cur_elem = randint(0, max_int)
        search_tree.insert(cur_elem)
    return search_tree


tree = BinarySearchTree()
tree.insert(5)
tree.insert(4)
tree.insert(6)
tree.insert(10)
tree.insert(9)
tree.insert(11)
# tree = fill_tree(tree)
tree.print_tree()
print(tree.tree_height(), ":height")
tree.delete_value(5)
tree.print_tree()
tree.delete_value(9)
tree.print_tree()
