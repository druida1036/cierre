package cierre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<DependenciaFuncional> conjunto = crear();
		System.out.println(Arrays.toString(conjunto.toArray()));
		System.out.println("Paso 1");
		conjunto = paso1(conjunto);
		System.out.println(Arrays.toString(conjunto.toArray()));
		System.out.println("Paso 2");
		conjunto = paso2(conjunto);
		// System.out.println(Arrays.toString(conjunto.toArray()));
		// permutation("ac");

	}

	private static List<DependenciaFuncional> paso2(List<DependenciaFuncional> conjunto) {
		List<DependenciaFuncional> copia = new ArrayList<>(conjunto);
		for (DependenciaFuncional df : conjunto) {
			System.out.println(df);
			char[] impArr = df.getImplicante().toCharArray();
			if (impArr.length > 1) {
				for (int i = 0; i < impArr.length; i++) {
					String nuevoImplicante = df.getImplicante().substring(0, i) + df.getImplicante().substring(i + 1);
					String cierre = calcularCierre(nuevoImplicante, copia);
					System.out.println("cierres: "+nuevoImplicante + "=" + cierre);
				}

			}

		}
		return copia;
	}

	private static String calcularCierre(String nuevoImplicante, List<DependenciaFuncional> conjunto) {
		String cierre = nuevoImplicante;
		String resultado = calculoImplicado(cierre, conjunto);
		cierre = resultado != null ? cierre + resultado : cierre;
		if (cierre.length() > 1) {
			String cierretmp = calcularCierraDelta1(cierre, conjunto);
			cierre = cierretmp;
		}
		if (cierre.length() >2) {
			int delta = 1;
			while (delta < cierre.length()){
				String base = cierre.substring(0, delta);
				String subconjunto = cierre.substring(delta, cierre.length());
//				System.out.println("delta "+base+" "+subconjunto);
				for (int i = 0; i < subconjunto.length(); i++) {
					String descriptor = base+subconjunto.substring(i, i+1);
					resultado = calculoImplicado(descriptor, conjunto);
					for (int j = 0; j < resultado.length(); j++) {
						if (!cierre.contains(resultado.substring(j, j+1))) {
							cierre = cierre+resultado.substring(j, j+1);
						}
					}
//					System.out.println(descriptor+resultado);
					
				}
				
				delta++;
				
			}
		}

		
//		cierre = resultado != null ? cierre + resultado : cierre;
		return cierre;
	}

	private static String calcularCierraDelta1(String cierre, List<DependenciaFuncional> conjunto) {
		String resultado;
		for (char descriptor : cierre.toCharArray()) {
			resultado = calculoImplicado(new StringBuilder().append(descriptor).toString(), conjunto);
			if (resultado != null && !cierre.contains(resultado)) {
				cierre = cierre + resultado;
			}
		}
		return cierre;
	}

	private static String calculoImplicado(String descriptor, List<DependenciaFuncional> conjunto) {
		String resultado = "";
		for (DependenciaFuncional df : conjunto) {
			if (df.getImplicante().equals(descriptor)) {
				resultado = resultado +df.getImplicado();
			}
		}
		return resultado;
	}

	private static List<DependenciaFuncional> paso1(List<DependenciaFuncional> conjunto) {
		List<DependenciaFuncional> copia = new ArrayList<>(conjunto);
		for (DependenciaFuncional df : conjunto) {
			if (df.getImplicado().toCharArray().length > 1) {
				copia.remove(df);
				for (int i = 0; i < df.getImplicado().toCharArray().length; i++) {
					String implicado = new StringBuffer().append(df.getImplicado().toCharArray()[i]).toString();
					DependenciaFuncional dfPrima = new DependenciaFuncional(df.getImplicante(), implicado);
					if (!copia.contains(dfPrima)) {
						copia.add(dfPrima);
					}

				}
			}
		}
		return copia;

	}

	private static List<DependenciaFuncional> crear() {
		List<DependenciaFuncional> lista = new ArrayList<>();
		DependenciaFuncional dependenciaFuncional = new DependenciaFuncional("ab", "c");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("d", "ef");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("c", "a");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("be", "c");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("bc", "d");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("cf", "b");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("cf", "d");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("acd", "b");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("ce", "a");
		lista.add(dependenciaFuncional);
		dependenciaFuncional = new DependenciaFuncional("ce", "f");
		lista.add(dependenciaFuncional);
		return lista;
	}

	public static void permutation(String s) {
		char[] original = s.toCharArray();
		Arrays.sort(original);
		char[] clone = new char[s.length()];
		boolean[] mark = new boolean[s.length()];
		Arrays.fill(mark, false);
		permute(original, clone, mark, 0, s.length());
	}

	private static void permute(char[] original, char[] clone, boolean[] mark, int length, int n) {
		if (length == n) {
			System.out.println(clone);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (mark[i] == true)
				continue;
			// dont use this state. to keep order of duplicate character
			if (i > 0 && original[i] == original[i - 1] && mark[i - 1] == false)
				continue;
			mark[i] = true;
			clone[length] = original[i];
			permute(original, clone, mark, length + 1, n);
			mark[i] = false;
		}

	}

}
