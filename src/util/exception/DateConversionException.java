package util.exception;




public class DateConversionException extends Exception {

	/**
	 * 
	 */
    private static final long serialVersionUID = 2308802499449158083L;
    private final String dataInvalida;
        
    public DateConversionException(String dataInvalida) {
        super("Data inválida: "+dataInvalida);
        this.dataInvalida = dataInvalida;
    }

    public String getDataInvalida() {
        return dataInvalida;
    }
        
        
	
	
	


}
