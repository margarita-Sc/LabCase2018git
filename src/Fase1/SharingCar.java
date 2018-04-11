package Fase1;

public class SharingCar {
	public static LstRequest mergeAlternateRequests(LstRequest a, LstRequest b) {
		LstRequest c = new LstRequest();
		DNode aNodeIt = a.header.next;
		DNode bNodeIt = b.header.next;
		while (c.size != (a.size + b.size)) {
			if (aNodeIt != a.trailer) {
				c.addLast(aNodeIt.elem);
				aNodeIt = aNodeIt.next;
			}
			if (bNodeIt != b.trailer) {
				c.addLast(bNodeIt.elem);
				bNodeIt = bNodeIt.next;
			}
		}
		System.out.println("Alternated Requests: ");
		return c;
	}

	public static LstRequest sharing(LstRequest a, LstRequest b) {
		LstRequest c = new LstRequest();
		int i = -1;

		if (!a.isEmpty() && !b.isEmpty()) {
			for (DNode aNodeIt = a.header.next; aNodeIt != a.trailer; aNodeIt = aNodeIt.next) {
				i = -1;
				for (DNode bNodeIt = b.header.next; bNodeIt != b.trailer; bNodeIt = bNodeIt.next) {
					if (aNodeIt.elem.source.equals(bNodeIt.elem.source)
							&& aNodeIt.elem.target.equals(bNodeIt.elem.target)) {
						c.addLast(bNodeIt.elem);
						i = 0;
					}
				}
				// de esta forma solo imprimimos el elemento de la primera lista 1 vez
				if (i == 0)
					c.addLast(aNodeIt.elem);
			}
		}

		System.out.println("Requests with same source and target in both lists: ");
		return c;
	}

	public static LstRequest sort(LstRequest a, Integer n) {
		// creamos lista auxiliar para no modificar la original
		LstRequest aux = new LstRequest();
		LstRequest b = new LstRequest();

		for (DNode nodeIt = a.header.next; nodeIt != a.trailer; nodeIt = nodeIt.next) {
			aux.addLast(nodeIt.elem);
		}

		if (n == 1) {
			while (!aux.isEmpty()) {
				String min = aux.header.next.elem.source;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer; nodeIt = nodeIt.next) {
					if (nodeIt.elem.source.compareTo(min) < 0) {
						min = nodeIt.elem.source;
					}
				}

				int i = 0;
				boolean found = false;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer && found == false; nodeIt = nodeIt.next) {

					if (nodeIt.elem.source.equals(min)) {
						found = true;
					} else
						i++;
				}

				b.addLast(aux.getAt(i));
				aux.removeAt(i);
			}

		} else if (n == 2) {
			while (!aux.isEmpty()) {
				String min = aux.header.next.elem.target;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer; nodeIt = nodeIt.next) {
					if (nodeIt.elem.target.compareTo(min) < 0) {
						min = nodeIt.elem.target;
					}
				}

				int i = 0;
				boolean found = false;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer && found == false; nodeIt = nodeIt.next) {

					if (nodeIt.elem.target.equals(min)) {
						found = true;
					} else
						i++;
				}

				b.addLast(aux.getAt(i));
				aux.removeAt(i);
			}

		} else {
			while (!aux.isEmpty()) {
				String min = aux.header.next.elem.id;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer; nodeIt = nodeIt.next) {
					if (nodeIt.elem.id.compareTo(min) < 0) {
						min = nodeIt.elem.id;
					}
				}

				int i = 0;
				boolean found = false;

				for (DNode nodeIt = aux.header.next; nodeIt != aux.trailer && found == false; nodeIt = nodeIt.next) {

					if (nodeIt.elem.id.equals(min)) {
						found = true;
					} else
						i++;
				}

				b.addLast(aux.getAt(i));
				aux.removeAt(i);
			}
		}
		System.out.println("Sorted requests: ");
		return b;
	}

	public static LstRequest removeDuplicates(LstRequest a) {
		// creamos lista auxiliar para no modificar la original
		LstRequest aux = new LstRequest();
		LstRequest b = new LstRequest();

		for (DNode nodeIt = a.header.next; nodeIt != a.trailer; nodeIt = nodeIt.next) {
			aux.addLast(nodeIt.elem);
		}

		while (!aux.isEmpty()) {
			boolean found = false;
			for (DNode nodeIt = b.header.next; nodeIt != b.trailer; nodeIt = nodeIt.next) {
				if (nodeIt.elem.equals(aux.getFirst())) {
					found = true;
				}
			}
			if (found == false) {
				b.addLast(aux.getFirst());
			}
			aux.removeFirst();
		}
		System.out.println("Duplicates removed:");
		return b;
	}

	public static LstRequest searchSource(LstRequest a, String source) {
		LstRequest b = new LstRequest();

		for (DNode nodeIt = a.header.next; nodeIt != a.trailer; nodeIt = nodeIt.next) {
			if (nodeIt.elem.source.equals(source)) {
				b.addLast(nodeIt.elem);
			}
		}
		System.out.println("Searching for requests with the introduced source...");
		return b;
	}

	public static LstRequest searchTarget(LstRequest a, String target) {
		LstRequest b = new LstRequest();

		for (DNode nodeIt = a.header.next; nodeIt != a.trailer; nodeIt = nodeIt.next) {
			if (nodeIt.elem.target.equals(target)) {
				b.addLast(nodeIt.elem);
			}
		}
		System.out.println("Searching for requests with the introduced target...");
		return b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Request a = new Request("user1", "Madrid", "Barcelona");
		Request b = new Request("2user", "Murcia", "Barcelona");
		Request c = new Request("user3", "Leon", "Murcia");
		Request d = new Request("4user", "Asturias", "Canarias");
		Request f = new Request("user2", "Madrid", "Pamplona");
		Request g = new Request("user3", "Madrid", "Pamplona");
		Request j = new Request("user6", "Leon", "Murcia");
		

		LstRequest one = new LstRequest();
		one.addFirst(a);
		one.addLast(b);
		// Madrid-Barcelona (user1), Galicia-Madrid(2user)

		LstRequest two = new LstRequest();
		two.addFirst(c);
		one.addLast(d);
		// Leon-Murcia(user3), Asturias-Canarias(4user)

		// mergeAlternateRequests
		System.out.println(mergeAlternateRequests(one, two));

		LstRequest three = new LstRequest();
		LstRequest four = new LstRequest();
		LstRequest five = new LstRequest();

		three.addFirst(j); // Leon-Murcia (user6)
		three.addLast(f); // Madrid-Pamplona (user2)

		four.addFirst(c); // Leon-Murcia (user3)
		four.addLast(b); // Galicia-Madrid (2user)
		four.addLast(d); // Asturias-Canarias (4user)
		four.addFirst(d);// Asturias-Canarias (4user)

		// sharing
		System.out.println(sharing(three, four));

		// sort
		System.out.println(sort(four, 7));

		// removeDuplicates
		System.out.println(removeDuplicates(four));

		for (int k = 0; k < 10; k++) {
			five.addFirst(g);
		}
		System.out.println(removeDuplicates(five));

		// searchSource
		System.out.println(searchSource(four, "Asturias"));

		System.out.println(searchSource(five, "Asturias"));

		System.out.println(searchSource(five, "Madrid"));

		// searchTarget
		System.out.println(searchTarget(one, "Barcelona"));

		System.out.println(searchTarget(five, "Pamplona"));

		System.out.println(searchTarget(five, "Madrid"));
		
		
		
		
		LstRequest lst1=new LstRequest();
		lst1.addLast(new Request("user1","Madrid","Barcelona"));
		lst1.addLast(new Request("user2","Alicante","Barcelona"));
		lst1.addLast(new Request("user3","Sevilla","Alicante"));
		lst1.addLast(new Request("user4","Valencia","Sevilla"));
		System.out.println(sort(lst1, 6));
		
		LstRequest lst2=new LstRequest();
		lst2.addLast(new Request("user5","Madrid","Sevilla"));
		lst2.addLast(new Request("user6","Madrid","Barcelona"));
		lst2.addLast(new Request("user7","Bilbao","Madrid"));
		lst2.addLast(new Request("user8","Valencia","Madrid"));
		lst2.addLast(new Request("user9","Valencia","Barcelona"));
		lst2.addLast(new Request("user6","Madrid","Barcelona"));
		lst2.addLast(new Request("user11","Sevilla","Madrid"));
		lst2.addLast(new Request("user12","Alicante","Barcelona"));
		
		System.out.println(sharing(lst1,lst2));
		
	}
	
}
