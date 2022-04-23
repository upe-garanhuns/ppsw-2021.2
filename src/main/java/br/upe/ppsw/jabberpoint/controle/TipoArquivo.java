package br.upe.ppsw.jabberpoint.controle;

public enum TipoArquivo {
	XML {
		@Override
		public IFilePresentationFormat obterTipoArquivo() {
			// TODO Auto-generated method stub
			return new XMLFormat();
		}
	}, 
	JSON {
		@Override
		public IFilePresentationFormat obterTipoArquivo() {
			// TODO Auto-generated method stub
			return new JSONFormat();
		}
	},
	
	HTML {
		@Override
		public IFilePresentationFormat obterTipoArquivo() {
			// TODO Auto-generated method stub
			return new HTMLFormat();
		}
	};
	
	public abstract IFilePresentationFormat obterTipoArquivo();
}
