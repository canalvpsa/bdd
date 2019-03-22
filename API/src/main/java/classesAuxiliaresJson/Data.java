package classesAuxiliaresJson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data {
	String dataAtualSemHora, dataAtualComHora, dataCalculada, dataAtual, dataRetorno;
	Date futuro;

	public String calcularData(int diasVencimento, boolean hora) {

		Date data = new Date();
		SimpleDateFormat formatadorSemHora = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatadorComHora = new SimpleDateFormat("dd-MM-yyyy 00:00:00");

		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, diasVencimento);
		futuro = cal.getTime();

		if(diasVencimento > 0){
			if(hora == true){
				dataRetorno = formatadorComHora.format(futuro);
			}else{
				dataRetorno = formatadorSemHora.format(futuro);
			}
		}
		if(diasVencimento == 0){
			if(hora == true){
				dataRetorno = formatadorComHora.format(data);
			}else{
				dataRetorno = formatadorSemHora.format(data);
			}
		}
		return dataRetorno;
	}


	public String retornaHorario(){
		Date hora = Calendar.getInstance().getTime();		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String dataFormatada = sdf.format(hora);
		return dataFormatada;
	}
}