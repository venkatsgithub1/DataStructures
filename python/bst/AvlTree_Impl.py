class Node:
    def __init__(self, value=None):
        self.value = value
        self.left_child = None
        self.right_child = None
        self.parent = None
        self.height = 1


class BinarySearchTree:
    def __init__(self):
        self.root = None

    def __repr__(self):
        if self.root==None: return ''
        content = '\n'  # to hold final string
        cur_nodes = [self.root]  # all nodes at current level
        cur_height = self.root.height  # height of nodes at current level
        # variable sized separator between elements
        sep = ' ' * (2**(cur_height - 1))
        while True:
            cur_height += -1  # decrement current height
            if len(cur_nodes) == 0:
                break
            cur_row = ' '
            next_row = ''
            next_nodes = []

            if all(n is None for n in cur_nodes):
                break

            for n in cur_nodes:

                if n == None:
                    cur_row += '   ' + sep
                    next_row += '   ' + sep
                    next_nodes.extend([None, None])
                    continue

                if n.value != None:
                    buf = ' ' * int((5 - len(str(n.value))) / 2)
                    cur_row += '%s%s%s' % (buf, str(n.value), buf) + sep
                else:
                    cur_row += ' ' * 5 + sep

                if n.left_child != None:
                    next_nodes.append(n.left_child)
                    next_row += ' /' + sep
                else:
                    next_row += '  ' + sep
                    next_nodes.append(None)

                if n.right_child != None:
                    next_nodes.append(n.right_child)
                    next_row += '\ ' + sep
                else:
                    next_row += '  ' + sep
                    next_nodes.append(None)

            content += (cur_height * '   ' + cur_row + '\n' +
                        cur_height * '   ' + next_row + '\n')
            cur_nodes = next_nodes
            sep = ' ' * int(len(sep) / 2)  # cut separator size in half
        return content

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
                self._inspect_insertion(current_node.left_child)
            else:
                self._insert(value, current_node.left_child)
        elif value > current_node.value:
            # go right.
            if current_node.right_child is None:
                current_node.right_child = Node(value)
                current_node.right_child.parent = current_node
                self._inspect_insertion(current_node.right_child)
            else:
                self._insert(value, current_node.right_child)
        else:
            print("value already in tree!")

    def print_tree(self):
        print("-----Tree starts-----")
        if self.root is not None:
            self._print_tree(self.root)
        print("-----Tree ends-----")

    def _print_tree(self, current_node):
        if current_node is not None:
            self._print_tree(current_node.left_child)
            print("{}, h={}".format(str(current_node.value), current_node.height))
            self._print_tree(current_node.right_child)

    def tree_height(self):
        if self.root is not None:
            return self._tree_height(self.root, 0)
        else:
            return 0

    def _tree_height(self, current_node, current_height):
        if current_node is None:
            return current_height
        left_height = self._tree_height(
            current_node.left_child, current_height + 1)
        right_height = self._tree_height(
            current_node.right_child, current_height + 1)
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
            return

        if node_parent is not None:
            node_parent.height=1 + max(self.get_height(node_parent.left_child), self.get_height(node_parent.right_child))
            self._inspect_deletion(node_parent)

    def _inspect_insertion(self,current_node,path=[]):
        if current_node.parent is None:
            return
        path = [current_node]+path

        left_height = self.get_height(current_node.parent.left_child)
        right_height = self.get_height(current_node.parent.right_child)

        if abs(left_height-right_height) > 1:
            path = [current_node.parent] + path
            self._rebalance_node(path[0],path[1],path[2])
            return
        
        new_height = 1 + current_node.height
        if new_height > current_node.parent.height:
            current_node.parent.height = new_height

        self._inspect_insertion(current_node.parent, path)
    
    def _inspect_deletion(self,current_node):
        if current_node is None:
            return
        
        left_height = self.get_height(current_node.parent.left_child)
        right_height = self.get_height(current_node.parent.right_child)

        if abs(left_height-right_height) > 1:
            y = self.taller_child(current_node)
            x = self.taller_child(y)

            self._rebalance_node(current_node, y, x)
        
        self._inspect_deletion(current_node.parent)

    def _rebalance_node(self,z,y,x):
        if y == z.left_child and x == y.left_child:
            self._right_rotate(z)
        elif y == z.left_child and x == y.right_child:
            self._left_rotate(y)
            self._right_rotate(z)
        elif y == z.right_child and x == y.right_child:
            self._left_rotate(z)
        elif y == z.right_child and x == y.left_child:
            self._right_rotate(y)
            self._left_rotate(z)
        else:
            raise Exception('z,y,x node configuration not recognized')

    def _right_rotate(self,z):
        sub_root = z.parent
        y = z.left_child
        t3 = y.right_child
        y.right_child = z
        z.parent = y
        z.left_child = t3
        if t3!=None:
            t3.parent = z
        y.parent = sub_root
        if y.parent == None:
            self.root = y
        else:
            if y.parent.left_child==z:
                y.parent.left_child = y
            else:
                y.parent.right_child = y
        z.height = 1 + max(self.get_height(z.left_child),self.get_height(z.right_child))
        y.height = 1 + max(self.get_height(y.left_child),self.get_height(y.right_child))

    def _left_rotate(self,z):
        sub_root = z.parent
        y = z.right_child
        t2 = y.left_child
        y.left_child = z
        z.parent = y
        z.right_child = t2
        if t2!=None:
            t2.parent = z
        y.parent=sub_root
        if y.parent == None:
            self.root = y
        else:
            if y.parent.left_child == z:
                y.parent.left_child = y
            else:
                y.parent.right_child = y

        z.height = 1 + max(self.get_height(z.left_child),self.get_height(z.right_child))
        y.height = 1 + max(self.get_height(y.left_child),self.get_height(y.right_child))

    def get_height(self,current_node):
        if current_node is None:
            return 0
        return current_node.height

    def taller_child(self,current_node):
        left = self.get_height(current_node.left_child)
        right = self.get_height(current_node.right_child)
        return current_node.left_child if left >=right else current_node.left_child
