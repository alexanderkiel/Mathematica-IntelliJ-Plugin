/*
 * Copyright (c) 2013 Patrick Scheibe
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package de.halirutan.mathematica.parsing.prattparser.parselets;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import de.halirutan.mathematica.parsing.MathematicaElementTypes;
import de.halirutan.mathematica.parsing.prattparser.CriticalParserError;
import de.halirutan.mathematica.parsing.prattparser.MathematicaParser;

/**
 * Parselet for symbols (identifier).
 *
 * @author patrick (3/27/13)
 */
public class SymbolParselet implements PrefixParselet {

  private final int myPrecedence;

  public SymbolParselet(int precedence) {
    this.myPrecedence = precedence;
  }

  @Override
  public MathematicaParser.Result parse(MathematicaParser parser) throws CriticalParserError {
    PsiBuilder.Marker symbolMark = parser.mark();
    final IElementType type = parser.getTokenType().equals(MathematicaElementTypes.IDENTIFIER) ?
        MathematicaElementTypes.SYMBOL_EXPRESSION :
        MathematicaElementTypes.STRINGIFIED_SYMBOL_EXPRESSION;
    parser.advanceLexer();
    symbolMark.done(type);
    return MathematicaParser.result(symbolMark, type, true);
  }

  public int getPrecedence() {
    return myPrecedence;
  }
}
