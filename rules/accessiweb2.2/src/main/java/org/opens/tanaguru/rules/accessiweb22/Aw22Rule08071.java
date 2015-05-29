/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.rules.accessiweb22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.lang.LangChangeChecker;
import org.opens.tanaguru.rules.elementchecker.lang.LangChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Implementation of the rule 8.7.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to
 * <a href="http://www.tanaguru.org/en/content/aw22-rule-8-7-1">the rule 8.7.1
 * design page.</a>
 *
 * @see
 * <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-7-1">
 * 8.7.1 rule specification</a>
 *
 */
public class Aw22Rule08071 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private final LangChecker ec = new LangChangeChecker();

    /**
     * Default constructor
     */
    public Aw22Rule08071() {
        super();
        setElementSelector(new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY));
        setElementChecker(ec);
    }

    @Override
    public void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, testSolutionHandler);
        // add extra remark at the end of the test, if the result is NMI
        if (testSolutionHandler.getTestSolution().equals(TestSolution.NEED_MORE_INFO)) {
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RemarkMessageStore.CHECK_SHORT_TEST_MSG);
        }
    }
    
    @Override
    public int getSelectionSize() {
        return ec.getNbOfElementsTested();
    }

}
