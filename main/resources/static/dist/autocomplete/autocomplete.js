/**
 A jQuery plugin for search data

 Author: Lorenzo Cioni - https://github.com/lorecioni
 */

(function ($) {
    $.fn.autocomplete = function (params) {
        // Selections
        var currentSelection = -1;
        var currentProposals = [];
        var proposals =
            // Default parameters
            params = $.extend({
                data: [],
                width: 200,
                height: 16,
                onSubmit: function (text) {
                },
                onBlur: function () {
                },
                onchange: function (text, obj) {
                }
            }, params);

        // Build messagess
        this.each(function () {
            var input = $(this);
            //Container
            var searchContainer = $('<div></div>')
                .addClass('autocomplete-container')
                .css('height', params.height);
            //Text input		
            var input = $('<input type="text" autocomplete="off" name="query">')
                .addClass(params.class)
                .attr('placeholder', params.placeholder)
                .addClass('autocomplete-input')
                .css({
                    'width': params.width,
                    'height': params.height
                });
            // Proposals
            proposals = $('<div></div>')
                .addClass('proposal-box')
                .css('width', input.width)
                .css('top', input.height)

            var proposalList = $('<ul></ul>')
                .addClass('proposal-list')
                .css('width', params.width + 'px');
            proposals.append(proposalList);

            // 获得焦点
            input.focus(function () {
                currentSelection = -1;
                proposalList.empty();
                nodataShow()
            })
            input.on("blur", function (e) { // 失焦
                setTimeout(function () {
                    currentSelection = -1;
                    proposalList.hide();
                    proposalList.empty();
                }, 200);
            })
            input.keydown(function (e) {
                switch (e.which) {
                    case 38: // Up arrow
                        e.preventDefault();
                        $('ul.proposal-list li').removeClass('selected');
                        if ((currentSelection - 1) >= 0) {
                            currentSelection--;
                            $("ul.proposal-list li:eq(" + currentSelection + ")")
                                .addClass('selected');
                        } else {
                            currentSelection = -1;
                        }
                        break;
                    case 40: // Down arrow
                        e.preventDefault();
                        if ((currentSelection + 1) < currentProposals.length) {
                            $('ul.proposal-list li').removeClass('selected');
                            currentSelection++;
                            $("ul.proposal-list li:eq(" + currentSelection + ")")
                                .addClass('selected');
                        }
                        break;
                    case 13: // Enter
                        if (currentSelection > -1) {
                            var text = $("ul.proposal-list li:eq(" + currentSelection + ")").html();
                            input.val(text);
                        }
                        currentSelection = -1;
                        proposalList.empty();
                        params.onSubmit(input.val());
                        proposalList.hide(); // miss 下拉框
                        break;
                    case 27: // Esc button
                        currentSelection = -1;
                        proposalList.empty();
                        input.val('');
                        break;
                }
            });

            input.on("keyup", function (e) {
                if (e.which != 13 && e.which != 27 && e.which != 38 && e.which != 40) {
                    currentProposals = [];
                    currentSelection = -1;
                    proposalList.empty();
                    if (input.val() != '') {
                        var word = "^" + input.val() + ".*";
                        showProposals(word);
                    } else {  // 为空 返回下拉列表
                        nodataShow()
                    }
                }
            })

            // 定义一个现实下拉列表的函数
            function showProposals(word) {
                proposalList.empty();
                for (var i = 0; i < params.data.length; i++) {
                    if (word.length == 0 || params.data[i].name.match(word)) {
                        currentProposals.push(params.data[i].name);
                        input.attr('data-id', params.data[i].id);
                        var element = $('<li></li>')
                            .html(params.data[i].name)
                            .attr('data-id', params.data[i].id)
                            .addClass('proposal')
                            .click(function (e) {
                                input.val($(this).html());
                                proposalList.empty();
                                params.onSubmit(input.val());
                                params.onchange($(this).data('id'), input);
                                proposalList.hide();
                                e.stopPropagation();
                                e.preventDefault();
                            })
                            .mouseenter(function () {
                                $(this).addClass('selected');
                            })
                            .mouseleave(function () {
                                $(this).removeClass('selected')
                            });
                        proposalList.append(element);
                    } else {
                        // 若不匹配，则隐藏
                        proposalList.hide();
                    }
                }
            }

            // 定义一个无参下拉列表的函数
            function nodataShow() {
                for (var i in params.data) {
                    currentProposals.push(params.data[i].name);
                    var element = $('<li></li>')
                        .html(params.data[i].name)
                        .attr('data-id', params.data[i].id)
                        .addClass('proposal')
                        .click(function (e) {
                            input.val($(this).html());
                            proposalList.empty();
                            params.onSubmit(input.val());
                            params.onchange($(this).data('id'), input)
                            proposalList.hide();
                            e.stopPropagation();
                            e.preventDefault();
                        })
                        .mouseenter(function () {
                            $(this).addClass('selected');
                        })
                        .mouseleave(function () {
                            $(this).removeClass('selected');
                        });
                    proposalList.append(element);
                }
                proposalList.show();
            }

            searchContainer.append(input);
            searchContainer.append(proposals);
            $(this).after(searchContainer);
        });

        return this;
    };
})(jQuery);
