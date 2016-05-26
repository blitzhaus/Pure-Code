package Model;
class AsciiNode {

	AsciiNode left, right; // The usual pointers.
	boolean isLeaf; // Is this a leaf?
	double value; // If so, we'll store the number here.
	char op; // If not, we need to know which operator.
	boolean getValue;
}