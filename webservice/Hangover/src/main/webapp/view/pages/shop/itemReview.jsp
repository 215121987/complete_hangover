<%@ include file="/view/common/taglib.jsp" %>
<div class="panel entry-content" id="tab-reviews">
    <div id="reviews">
        <div id="comments">
            <c:choose>
                <c:when test="${not empty itemReviews && fn:length(itemReviews)>0}">
                    <h2>${fn:length(itemReviews)} reviews for ${item.name}</h2>
                    <ol class="commentlist">
                        <c:forEach items="${itemReviews}" var="review">
                            <li
                                    class="comment byuser comment-author-admin bypostauthor even thread-even depth-1" id="li-comment-${review.id}">
                                <div id="comment-${review.id}" class="comment_container">
                                    <img alt='admin'
                                         class='avatar avatar-60 photo' height='60' width='60'/>
                                    <div class="comment-text">
                                        <div itemprop="reviewRating" itemscope itemtype="http://schema.org/Rating"
                                             class="star-rating" title="Rated 5 out of 5">
                                            <span style="width:100%"><strong itemprop="ratingValue">5</strong> out of 5</span>
                                        </div>
                                        <p class="meta">
                                            <strong itemprop="author">${review.user.name}</strong> &ndash;
                                            <time itemprop="datePublished">January 27,
                                                2014
                                            </time>
                                            :
                                        </p>
                                        <div itemprop="description" class="description"><p>${review.comment}</p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ol>
                </c:when>
                <c:otherwise>
                    <h2>Be the first to write review</h2>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="review_form_wrapper">
            <div id="review_form">
                <div id="respond" class="comment-respond">
                    <form action="#" method="post" id="commentform" class="comment-form">
                        <p class="comment-form-author">
                            <label for="author">Name <span class="required">*</span></label>
                            <input id="author" name="author" type="text" value="" size="30" aria-required="true" required/>
                        </p>

                        <p class="comment-form-email">
                            <label for="email">Email <span class="required">*</span></label>
                            <input id="email" name="email" type="text" value="" size="30" aria-required="true" required/>
                        </p>
                        <p class="comment-form-rating">
                            <label for="rating">Your Rating</label>
                            <select name="rating" id="rating">
                                <option value="">Rate&hellip;</option>
                                <option value="5">Perfect</option>
                                <option value="4">Good</option>
                                <option value="3">Average</option>
                                <option value="2">Not that bad</option>
                                <option value="1">Very Poor</option>
                            </select>
                        </p>
                        <p class="comment-form-comment">
                            <label for="comment">Your Review</label>
                            <textarea id="comment" name="comment" cols="45" rows="8" aria-required="true"></textarea>
                        </p>
                        <p class="form-submit">
                            <input name="submit" type="submit" id="submit" class="submit" value="Submit"/>
                            <input type='hidden' name='comment_post_ID' value='${item.id}' id='comment_post_ID'/>
                            <input type='hidden' name='comment_parent' id='comment_parent' value='0'/>
                        </p>
                    </form>
                </div>
                <!-- #respond -->
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
