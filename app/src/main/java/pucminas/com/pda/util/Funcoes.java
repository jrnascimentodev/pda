package pucminas.com.pda.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Funcoes
{
    //Data Atual Dia, Mês e Ano
    public static String DataAtual()
    {
        String DataAtual = null;

        try
        {
            SimpleDateFormat dateFormat_hora = new SimpleDateFormat("dd/MM/yyyy");

            Date data = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);

            Date dataAtual = cal.getTime();
            DataAtual = dateFormat_hora.format(dataAtual);
        }
        catch (Exception e)
        {
            Log.e("Erro",e.getMessage());
        }

        return DataAtual;
    }

    //Retorna tipo Date, Dia, Mes, Ano
    public static Date ConvertData(String data)
    {
        Date  date = null;

        try
        {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            date = formato.parse(data);

        }
        catch(Exception Ex)
        {
            Ex.getMessage();
        }

        return date;
    }

    //Converte Data para Dia, Mês e Ano formato do Banco de Dados
    public static Date ConvertDataBancoDados(String Data)
    {
        java.sql.Date  Data_Formatada = null;

        try
        {
            SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
            Data_Formatada = new java.sql.Date(Format.parse(Data).getTime());

        }
        catch(Exception Ex)
        {
        }

        return Data_Formatada;
    }
}
