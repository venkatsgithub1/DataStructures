from DataStructures.python.bst import Bst_Impl
import sys


def validate_bst(node, min_value=-sys.maxsize, max_value=sys.maxsize):
    if node is None:
        # node is blank.
        return True

    if (min_value < node.value < max_value and
            validate_bst(node.left_child, min_value, node.value) and
            validate_bst(node.right_child, node.value, max_value)):
        return True
    else:
        return False


tree = Bst_Impl.BinarySearchTree()
tree.insert(5)
tree.insert(4)
tree.insert(6)
print("tree:", validate_bst(tree.root))
