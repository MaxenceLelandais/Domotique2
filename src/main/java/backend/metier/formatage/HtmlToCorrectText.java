package backend.metier.formatage;

public class HtmlToCorrectText {

	private String[][] transformationList = { { "à", "&agrave;" }, { "À", "&Agrave;" }, { "á", "&aacute;" },
			{ "Á", "&Aacute;" }, { "â", "&acirc;" }, { "Â", "&Acirc;" }, { "ã", "&atilde;" }, { "Ã", "&Atilde;" },
			{ "ä", "&auml;" }, { "Ä", "&Auml;" }, { "å", "&aring;" }, { "Å", "&Aring;" }, { "æ", "&aelig;" },
			{ "Æ", "&AElig;" }, { "è", "&egrave;" }, { "È", "&Egrave;" }, { "é", "&eacute;" }, { "É", "&Eacute;" },
			{ "ê", "&ecirc;" }, { "Ê", "&Ecirc;" }, { "ë", "&euml;" }, { "Ë", "&Euml;" }, { "ì", "&igrave;" },
			{ "Ì", "&Igrave;" }, { "í", "&iacute;" }, { "Í", "&Iacute;" }, { "î", "&icirc;" }, { "Î", "&Icirc;" },
			{ "ï", "&iuml;" }, { "Ï", "&Iuml;" }, { "ò", "&ograve;" }, { "Ò", "&Ograve;" }, { "ó", "&oacute;" },
			{ "Ó", "&Oacute;" }, { "ô", "&ocirc;" }, { "Ô", "&Ocirc;" }, { "õ", "&otilde;" }, { "Õ", "&Otilde;" },
			{ "ö", "&ouml;" }, { "Ö", "&Ouml;" }, { "ø", "&oslash;" }, { "Ø", "&Oslash;" }, { "ù", "&ugrave;" },
			{ "Ù", "&Ugrave;" }, { "ú", "&uacute;" }, { "Ú", "&Uacute;" }, { "û", "&ucirc;" }, { "Û", "&Ucirc;" },
			{ "ü", "&uuml;" }, { "Ü", "&Uuml;" }, { "ñ", "&ntilde;" }, { "Ñ", "&Ntilde;" }, { "ý", "&yacute;" },
			{ "Ý", "&Yacute;espace insécable" }, { " ", "<br>" }, { "…", "&hellip;" }, { "!", "!" }, { "¡", "&iexcl;" },
			{ "?", "?" }, { "¿", "&iquest;" }, { "•", "&bull;" }, { "&", "&amp;" }, { "'", "&#039;" },
			{ "-", "&mdash;" }, { "-", "&ndash;" }, { "¶", "&para;" }, { "§", "&sect;" }, { "‡", "&Dagger;" },
			{ "†", "&dagger" }, { "←", "&larr;" }, { "↑", "&uarr;" }, { "→", "&rarr;" }, { "↓", "&darr;" },
			{ "&", "&amp;" }, { "\"", "&laquo;" }, { "\"", "&raquo;" }, { "'", "&lsquo;" }, { "'", "&rsquo;" },
			{ "\"", "&ldquo;" }, { "\"", "&rdquo;" }, { "‹", "&lsaquo;" }, { "›", "&rsaquo;" }, { "‚", "&sbquo;" },
			{ "„", "&bdquo;" }, { "€", "&euro;" }, { "$", "&#36" }, { "£", "&pound;" }, { "©", "&copy;" },
			{ "™", "&trade;" }, { "®", "&reg;" }, { "+", "+" }, { "×", "&times;" }, { "±", "&plusmn;" }, { "%", "%" },
			{ "−", "&minus;" }, { "÷", "&divide;" }, { "⁄", "&frasl;" }, { "=", "=" }, { "≡", "&equiv;" },
			{ "<", "&lt;" }, { "≤", "&le;" }, { "≠", "&ne;" }, { "≈", "&asymp;" }, { ">", "&gt;" }, { "≥", "&ge;" },
			{ "½", "&frac12;" }, { "¼", "&frac14;" }, { "¾", "&frac34;" }, { "°", "&deg;" },{"ç","&ccedil;"},
			{"œ","&oelig;"}};

	public String changeCode(String codeHtml) {
		
		String goodCode = codeHtml;

		for (String[] format : transformationList) {
			goodCode = goodCode.replace(format[1], format[0]);
		}
		return goodCode;
	}

	public String suppBalise(String codeHtml) {
		
		String goodCode = codeHtml;
		int postStart;
		int postEnd;
		
		while (true) {
			
			postStart = goodCode.indexOf("<");
			postEnd = goodCode.indexOf(">") + 1;

			if (postStart != -1 && postEnd != -1) {
				goodCode = goodCode.replace(goodCode.substring(postStart, postEnd), "");
			} else {
				break;
			}
		}
		return goodCode;
	}

}
