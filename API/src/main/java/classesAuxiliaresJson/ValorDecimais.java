package classesAuxiliaresJson;


public class ValorDecimais {


	public Number tirarDecimais(float valor){
		double x = valor;
		Number valorFormatado;
		if (x == Math.rint(x)) {
			valorFormatado = (int) x;
		} else {
			valorFormatado = x;
		}

		return valorFormatado;
	}
}