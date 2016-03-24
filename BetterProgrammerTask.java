import java.util.*;

public class BetterProgrammerTask {

    public static Object[] reverseArray(Object[] a) {
        Object[] tmparray = new Object[a.length];
    	for (int i=0;i<a.length;i++) {
    		tmparray[i]=a[a.length-i-1];
    	}
    	return tmparray;
    	/*
          Please implement this method to
          return a new array where the order of elements has been reversed from the original
          array.
         */
    }
    
    public static int getClosestToZero(int[] a) {
		/*
		  Please implement this method to
		  return the number in the array that is closest to zero.
		  If there are two equally close to zero elements like 2 and -2
		  - consider the positive element to be "closer" to zero.
		 */
    	int x=a[0];
        for (int i=1;i<a.length;i++) {
            if (Math.abs(x)!=Math.abs(a[i])) {
            	if (Math.abs(x)>Math.abs(a[i])) {
            		x=a[i];
            	}	
            } else {
            	if (a[i]>x) {
            		x=a[i];
            	}
            }
        }
        return x;
    }
    
    public static class WriteOnceMap<K, V> extends HashMap<K, V> {

        public V put(K key, V value) {
			/*
			 * WriteOnceMap is a map that does not allow changing value for a
			 * particular key. It means that put() method should throw
			 * IllegalArgumentException if the key is already assosiated with
			 * some value in the map.
			 * 
			 * Please implement this method to conform to the above description
			 * of WriteOnceMap.
			 */
        	if (this.get(key)==null) {
            	return super.put(key, value);
            } else {
            	throw new IllegalArgumentException();
            }
        }


        public void putAll(Map<? extends K, ? extends V> m) {
			/*
			 * Pleaase implement this method to conform to the description of
			 * WriteOnceMap above. It should either (1) copy all of the mappings
			 * from the specified map to this map or (2) throw
			 * IllegalArgumentException and leave this map intact if the
			 * parameter already contains some keys from this map.
			 */
        	for (K key : m.keySet()) {
        	    if (this.get(key)!=null) {
        	    	throw new IllegalArgumentException();
        	    }
        	}
        	for (java.util.Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
        	    this.put(entry.getKey(), entry.getValue());
        	}
        }
    }
	
	public static int sumOfTwoLargestElements(int[] a) {
        /*
          Please implement this method to
          return the sum of the two largest numbers in a given array.
         */
		int x1=Integer.MIN_VALUE,x2=Integer.MIN_VALUE;
		for (int i=0;i<a.length;i++) {
			if (a[i]>x1) {
				if (x1>x2) {
					x2=a[i];
				} else {
					x1=a[i];					
				}
			} else {
				if (a[i]>x2) {
					x2=a[i];
				}
			}
		}
		return x2+x1;
    }
	
    public static List<Integer> getPerfectNumbers(int from, int to) {
        /*
          Please implement this method to
          return a list of all perect numbers in the given range inclusively.
          A perfect number is defined as a positive integer which is the sum of its positive divisors not including the number itself.
          For example: 6 is a perfect number because 6 = 1 + 2 + 3 (1, 2, 3 are divisors of 6)
          28 is also a perfect number: 28 = 1 + 2 + 4 + 7 + 14
         */
    	List<Integer> perfect = new ArrayList<Integer>();
    	for (int i=from;i<=to;i++) {
    		int divsum = 0;
    		for (int j=1;j<i;j++) {
    			if (i % j == 0) {
    				divsum+=j;
    			}
    		}
    		if (i==divsum) {
    			perfect.add(i);
    			System.out.println(i);
    		}
    	}
    	return perfect;
    }
    
 // Please do not change this interface
    public static interface Node {
        int getValue();
        List<Node> getChildren();
    }
    
    public class TreeNode implements Node {
    	private int value;
    	private List<Node> children;
		
    	public TreeNode(int val) {
    		this.value = val;
    		children = new ArrayList<Node>();
    	}
    	
    	@Override
		public int getValue() {
			return value;
		}
		
		public void setValue(int v) {
			this.value = v;
		}

		@Override
		public List<Node> getChildren() {
			return children;
		}
		
		public void addChild(Node node) {
			children.add(node);
		}
    }

    public static List<Node> traverseTreeInWidth(Node root) {
    	List<Node> nodeList = new ArrayList<Node>();
    	Queue<Node> queue = new java.util.LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = (Node) queue.poll();
            nodeList.add(node);
            if (!node.getChildren().isEmpty()) {
            	Iterator<Node> iterator = node.getChildren().iterator();
            	while (iterator.hasNext()) queue.offer((Node)iterator.next());
            }
        }
        return nodeList;
    	/*
          Please implement this method to
          traverse the tree in width and return a list of all passed nodes.

          The list should start with the root node, next
          it should contain all second-level nodes, then third-level nodes etc.

          The method shall work optimally with large trees.
         */
    }
    
    public static int getLargestRootToLeafSum(Node root) {
        Node largest = null;
    	Iterator i = root.getChildren().iterator();
    	if (i.hasNext()) {
        	while (i.hasNext()) {
        		Node child = (Node) i.next();
        		if (largest==null) {
        			largest=child;
        		} else {
            		if (child.getValue()>largest.getValue()) {
            			largest=child;
            		}	
        		}
        	}
        	return root.getValue() + getLargestRootToLeafSum(largest);
    	} else {
    		return root.getValue();
    	}
    }
	
    public static void main(String[] args) {
    	int[] arr = {3,1,-2,4};
    	//System.out.println(sumOfTwoLargestElements(arr));
    	List<Integer> perfectList = getPerfectNumbers(1,1000);
    }
}