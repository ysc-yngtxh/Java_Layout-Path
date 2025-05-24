package com.example.v3.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericTokenParser {

	private final String openToken;
	private final String closeToken;
	private final TokenHandler handler;

	public String parse(String text) {
		if (text != null && !text.isEmpty()) {
			int start = text.indexOf(this.openToken);
			if (start == -1) {
				return text;
			} else {
				char[] src = text.toCharArray();
				int offset = 0;
				StringBuilder builder = new StringBuilder();
				StringBuilder expression = null;

				do {
					if (start > 0 && src[start - 1] == '\\') {
						builder.append(src, offset, start - offset - 1).append(this.openToken);
						offset = start + this.openToken.length();
					} else {
						if (expression == null) {
							expression = new StringBuilder();
						} else {
							expression.setLength(0);
						}

						builder.append(src, offset, start - offset);
						offset = start + this.openToken.length();

						int end;
						for (end = text.indexOf(this.closeToken, offset); end > -1; end = text.indexOf(this.closeToken, offset)) {
							if (end <= offset || src[end - 1] != '\\') {
								expression.append(src, offset, end - offset);
								break;
							}

							expression.append(src, offset, end - offset - 1).append(this.closeToken);
							offset = end + this.closeToken.length();
						}

						if (end == -1) {
							builder.append(src, start, src.length - start);
							offset = src.length;
						} else {
							builder.append(this.handler.handleToken(expression.toString()));
							offset = end + this.closeToken.length();
						}
					}

					start = text.indexOf(this.openToken, offset);
				} while (start > -1);

				if (offset < src.length) {
					builder.append(src, offset, src.length - offset);
				}

				return builder.toString();
			}
		} else {
			return "";
		}
	}

}
