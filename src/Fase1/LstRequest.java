package Fase1;

public class LstRequest implements IList {

	DNode header;
	DNode trailer;
	int size = 0;

	public LstRequest() {
		header = new DNode(null);
		trailer = new DNode(null);
		header.next = trailer;
		trailer.prev = header;
	}

	public void addFirst(Request elem) {
		DNode newNode = new DNode(elem);
		newNode.next = header.next;
		newNode.prev = header;
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	public void addLast(Request elem) {
		DNode newNode = new DNode(elem);
		newNode.next = trailer;
		newNode.prev = trailer.prev;
		trailer.prev.next = newNode;
		trailer.prev = newNode;
		size++;
	}

	public void insertAt(int index, Request elem) {
		DNode newNode = new DNode(elem);
		int i = 0;
		boolean inserted = false;
		for (DNode nodeIt = header; nodeIt != trailer && inserted == false; nodeIt = nodeIt.next) {
			if (i == index) {
				newNode.next = nodeIt.next;
				newNode.prev = nodeIt;
				nodeIt.next.prev = newNode;
				nodeIt.next = newNode;
				inserted = true;
				size++;
			}
			++i;
		}
		if (!inserted)
			System.out.println("DList: Insertion out of bounds");
	}

	public boolean isEmpty() {
		return (header.next == trailer);
	}

	public boolean contains(Request elem) {
		boolean found = false;
		for (DNode nodeIt = header.next; nodeIt != trailer && found == false; nodeIt = nodeIt.next) {
			if (nodeIt.elem.equals(elem)) {
				found = true;
			}
		}
		return found;
	}

	public int getIndexOf(Request elem) {
		int index = -1;
		int pos = 0;
		for (DNode nodeIt = header.next; nodeIt != trailer && index == -1; nodeIt = nodeIt.next) {
			if (nodeIt.elem.equals(elem)) {
				index = pos;
			}
			++pos;

		}
		return index;
	}

	public void removeFirst() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev = header;
		size--;
	}

	public void removeLast() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		trailer.prev = trailer.prev.prev;
		trailer.prev.next = trailer;
		size--;
	}

	public void removeAll(Request elem) {
		for (DNode nodeIt = header.next; nodeIt != trailer; nodeIt = nodeIt.next) {
			if (nodeIt.elem.equals(elem)) {
				nodeIt.prev.next = nodeIt.next;
				nodeIt.next.prev = nodeIt.prev;
				size--;

			}
		}
	}

	public void removeAt(int index) {
		int i = 0;
		boolean removed = false;
		for (DNode nodeIt = header.next; nodeIt != trailer && removed == false; nodeIt = nodeIt.next) {
			if (i == index) {
				nodeIt.prev.next = nodeIt.next;
				nodeIt.next.prev = nodeIt.prev;
				removed = true;
				size--;

			}
			++i;
		}
		if (!removed)
			System.out.println("DList: Deletion out of bounds");
	}

	public int getSize() {
		return size;
	}

	public Request getFirst() {
		Request result = null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else
			result = header.next.elem;
		return result;
	}

	public Request getLast() {
		Request result = null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else
			result = trailer.prev.elem;
		return result;
	}

	public Request getAt(int index) {
		int i = 0;
		Request result = null;
		for (DNode nodeIt = header.next; nodeIt != trailer && result == null; nodeIt = nodeIt.next) {
			if (i == index) {
				result = nodeIt.elem;
			}
			++i;
		}
		if (result == null)
			System.out.println("DList: Get out of bounds");
		return result;
	}

	public String toString() {
		String result = null;
		for (DNode nodeIt = header.next; nodeIt != trailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "Source: " + String.valueOf(nodeIt.elem.source) + ", " + "Target: "
						+ String.valueOf(nodeIt.elem.target) + ", " + "User: " + String.valueOf(nodeIt.elem.id);
			} else {
				result += "; " + "Source: " + String.valueOf(nodeIt.elem.source) + ", " + "Target: "
						+ String.valueOf(nodeIt.elem.target) + ", " + "User: " + String.valueOf(nodeIt.elem.id);
			}
		}
		return result == null ? "empty" : result;
	}
	public boolean equals(LstRequest list) {
        if (list==this) return true;
        if (list.getSize()!=this.getSize()) return false;
        for (int i=0; i<this.getSize(); i++) {
              Request r1=this.getAt(i);
              Request r2=list.getAt(i);
              if (!r1.equals(r2)) return false;
        }
        return true;
  }
}
