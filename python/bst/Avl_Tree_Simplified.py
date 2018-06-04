class Node:
    def __init__(self, value=None, parent=None):
        self.value = value
        self.left = None
        self.right = None
        self.parent = None
        self.height = 1


class AVL_Tree:
    def insert(self, root, key):
        if not root:
            # root is blank, insert here.
            return Node(value=key)
        elif key > root.value:
            # right
            root.right = self.insert(root.right, key)
        else:
            # left
            root.left = self.insert(root.left, key)

        root.height = 1 + max(self.get_height(root.left),
                              self.get_height(root.right))

        balance = self.get_balance(root)

        # # if node is unblanaced. One of four cases.
        # # case 1:
        # if balance > 1 and key < root.left.value:
        #     # left left rotate right.
        #     return self.rotate_right(root)
        # # case 2:
        # if balance > 1 and key > root.left.value:
        #     # left right rotate left on root.left and right on root.
        #     root.left = self.rotate_left(root.left)
        #     return self.rotate_right(root)
        # # case 3:
        # if balance < -1 and key > root.right.value:
        #     # right right rotate left.
        #     return self.rotate_left(root)
        # # case 4:
        # if balance < -1 and key < root.right.value:
        #     # right left rotate right on root.right and left on root.
        #     root.right = self.rotate_right(root.right)
        #     return self.rotate_left(root)
        return self.rebalance(balance, root, key)

    def rebalance(self, balance, root, key):
        # if node is unblanaced. One of four cases.
        # case 1:
        if balance > 1 and key < root.left.value:
            # left left rotate right.
            return self.rotate_right(root)
        # case 2:
        if balance > 1 and key > root.left.value:
            # left right rotate left on root.left and right on root.
            root.left = self.rotate_left(root.left)
            return self.rotate_right(root)
        # case 3:
        if balance < -1 and key > root.right.value:
            # right right rotate left.
            return self.rotate_left(root)
        # case 4:
        if balance < -1 and key < root.right.value:
            # right left rotate right on root.right and left on root.
            root.right = self.rotate_right(root.right)
            return self.rotate_left(root)
        return root

    def rotate_right(self, z):
        y = z.left
        temp = y.right
        y.right = z
        z.right = temp
        # update heights.
        z.height = 1 + max(self.get_height(z.right), self.get_height(z.left))
        y.height = 1 + max(self.get_height(y.right), self.get_height(y.left))
        return y

    def rotate_left(self, z):
        y = z.right
        temp = y.left
        y.left = z
        z.right = temp
        # update heights.
        z.height = 1 + max(self.get_height(z.right), self.get_height(z.left))
        y.height = 1 + max(self.get_height(y.right), self.get_height(y.left))
        return y

    def get_height(self, root):
        if not root:
            return 0
        return root.height

    def get_balance(self, root):
        if not root:
            return 0
        return self.get_height(root.left) - self.get_height(root.right)

    def delete(self, root, key):
        if not root:
            return root

        elif key < root.val:
            root.left = self.delete(root.left, key)

        elif key > root.val:
            root.right = self.delete(root.right, key)

        else:
            if root.left is None:
                temp = root.right
                root = None
                return temp

            elif root.right is None:
                temp = root.left
                root = None
                return temp

            temp = self.getMinValueNode(root.right)
            root.val = temp.val
            root.right = self.delete(root.right, key)

        if root is None:
            return root

        root.height = 1 + max(self.get_height(root.right),
                              self.get_height(root.left))

        balance = self.get_balance(root)

        return self.rebalance(balance, root, key)

    def getMinValueNode(self, root):
        # smaller values are on left of the tree.
        if root is None or root.left is None:
            return root

        return self.getMinValueNode(root.left)

    def pre_order_traversal(self, root):
        if not root:
            return
        print(root.value)
        self.pre_order_traversal(root.left)
        self.pre_order_traversal(root.right)
