package Fase1;

public interface IList {
	public void addFirst(Request newElem);

	public void addLast(Request newElem);

	public void removeFirst();

	public void removeLast();

	public void insertAt(int index, Request newElem);

	public boolean isEmpty();

	public boolean contains(Request elem);

	public int getSize();

	public int getIndexOf(Request elem);

	public Request getFirst();

	public Request getLast();

	public Request getAt(int index);

	public void removeAll(Request elem);

	public void removeAt(int index);
}
