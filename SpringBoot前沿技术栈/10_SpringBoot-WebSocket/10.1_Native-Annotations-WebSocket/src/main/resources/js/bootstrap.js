/*!
  * Bootstrap v5.1.0 (https://getbootstrap.com/)
  * Copyright 2011-2021 The Bootstrap Authors (https://github.com/twbs/bootstrap/graphs/contributors)
  * Licensed under MIT (https://github.com/twbs/bootstrap/blob/main/LICENSE)
  */
!function (t, e) {
    "object" == typeof exports && "undefined" != typeof module ? module.exports = e(require("@popperjs/core")) : "function" == typeof define && define.amd ? define(["@popperjs/core"], e) : (t = "undefined" != typeof globalThis ? globalThis : t || self).bootstrap = e(t.Popper)
}(this, (function (t) {
    "use strict";

    function e(t) {
        if (t && t.__esModule) return t;
        var e = Object.create(null);
        return t && Object.keys(t).forEach((function (i) {
            if ("default" !== i) {
                var s = Object.getOwnPropertyDescriptor(t, i);
                Object.defineProperty(e, i, s.get ? s : {
                    enumerable: !0,
                    get: function () {
                        return t[i]
                    }
                })
            }
        })), e.default = t, Object.freeze(e)
    }

    var i = e(t);
    const s = t => {
            let e = t.getAttribute("data-bs-target");
            if (!e || "#" === e) {
                let i = t.getAttribute("href");
                if (!i || !i.includes("#") && !i.startsWith(".")) return null;
                i.includes("#") && !i.startsWith("#") && (i = "#" + i.split("#")[1]), e = i && "#" !== i ? i.trim() : null
            }
            return e
        }, n = t => {
            const e = s(t);
            return e && document.querySelector(e) ? e : null
        }, o = t => {
            const e = s(t);
            return e ? document.querySelector(e) : null
        }, r = t => {
            t.dispatchEvent(new Event("transitionend"))
        },
        a = t => !(!t || "object" != typeof t) && (void 0 !== t.jquery && (t = t[0]), void 0 !== t.nodeType),
        l = t => a(t) ? t.jquery ? t[0] : t : "string" == typeof t && t.length > 0 ? document.querySelector(t) : null,
        c = (t, e, i) => {
            Object.keys(i).forEach(s => {
                const n = i[s], o = e[s],
                    r = o && a(o) ? "element" : null == (l = o) ? "" + l : {}.toString.call(l).match(/\s([a-z]+)/i)[1].toLowerCase();
                var l;
                if (!new RegExp(n).test(r)) throw new TypeError(`${t.toUpperCase()}: Option "${s}" provided type "${r}" but expected type "${n}".`)
            })
        },
        h = t => !(!a(t) || 0 === t.getClientRects().length) && "visible" === getComputedStyle(t).getPropertyValue("visibility"),
        d = t => !t || t.nodeType !== Node.ELEMENT_NODE || !!t.classList.contains("disabled") || (void 0 !== t.disabled ? t.disabled : t.hasAttribute("disabled") && "false" !== t.getAttribute("disabled")),
        u = t => {
            if (!document.documentElement.attachShadow) return null;
            if ("function" == typeof t.getRootNode) {
                const e = t.getRootNode();
                return e instanceof ShadowRoot ? e : null
            }
            return t instanceof ShadowRoot ? t : t.parentNode ? u(t.parentNode) : null
        }, g = () => {
        }, p = t => {
            t.offsetHeight
        }, f = () => {
            const {jQuery: t} = window;
            return t && !document.body.hasAttribute("data-bs-no-jquery") ? t : null
        }, _ = [], m = () => "rtl" === document.documentElement.dir, b = t => {
            var e;
            e = () => {
                const e = f();
                if (e) {
                    const i = t.NAME, s = e.fn[i];
                    e.fn[i] = t.jQueryInterface, e.fn[i].Constructor = t, e.fn[i].noConflict = () => (e.fn[i] = s, t.jQueryInterface)
                }
            }, "loading" === document.readyState ? (_.length || document.addEventListener("DOMContentLoaded", () => {
                _.forEach(t => t())
            }), _.push(e)) : e()
        }, v = t => {
            "function" == typeof t && t()
        }, y = (t, e, i = !0) => {
            if (!i) return void v(t);
            const s = (t => {
                if (!t) return 0;
                let {
                    transitionDuration: e,
                    transitionDelay: i
                } = window.getComputedStyle(t);
                const s = Number.parseFloat(e), n = Number.parseFloat(i);
                return s || n ? (e = e.split(",")[0], i = i.split(",")[0], 1e3 * (Number.parseFloat(e) + Number.parseFloat(i))) : 0
            })(e) + 5;
            let n = !1;
            const o = ({target: i}) => {
                i === e && (n = !0, e.removeEventListener("transitionend", o), v(t))
            };
            e.addEventListener("transitionend", o), setTimeout(() => {
                n || r(e)
            }, s)
        }, w = (t, e, i, s) => {
            let n = t.indexOf(e);
            if (-1 === n) return t[!i && s ? t.length - 1 : 0];
            const o = t.length;
            return n += i ? 1 : -1, s && (n = (n + o) % o), t[Math.max(0, Math.min(n, o - 1))]
        }, E = /[^.]*(?=\..*)\.|.*/, A = /\..*/, T = /::\d+$/, C = {};
    let k = 1;
    const L = {mouseenter: "mouseover", mouseleave: "mouseout"},
        S = /^(mouseenter|mouseleave)/i,
        O = new Set(["click", "dblclick", "mouseup", "mousedown", "contextmenu", "mousewheel", "DOMMouseScroll", "mouseover", "mouseout", "mousemove", "selectstart", "selectend", "keydown", "keypress", "keyup", "orientationchange", "touchstart", "touchmove", "touchend", "touchcancel", "pointerdown", "pointermove", "pointerup", "pointerleave", "pointercancel", "gesturestart", "gesturechange", "gestureend", "focus", "blur", "change", "reset", "select", "submit", "focusin", "focusout", "load", "unload", "beforeunload", "resize", "move", "DOMContentLoaded", "readystatechange", "error", "abort", "scroll"]);

    function N(t, e) {
        return e && `${e}::${k++}` || t.uidEvent || k++
    }

    function D(t) {
        const e = N(t);
        return t.uidEvent = e, C[e] = C[e] || {}, C[e]
    }

    function I(t, e, i = null) {
        const s = Object.keys(t);
        for (let n = 0, o = s.length; n < o; n++) {
            const o = t[s[n]];
            if (o.originalHandler === e && o.delegationSelector === i) return o
        }
        return null
    }

    function x(t, e, i) {
        const s = "string" == typeof e, n = s ? i : e;
        let o = j(t);
        return O.has(o) || (o = t), [s, n, o]
    }

    function P(t, e, i, s, n) {
        if ("string" != typeof e || !t) return;
        if (i || (i = s, s = null), S.test(e)) {
            const t = t => function (e) {
                if (!e.relatedTarget || e.relatedTarget !== e.delegateTarget && !e.delegateTarget.contains(e.relatedTarget)) return t.call(this, e)
            };
            s ? s = t(s) : i = t(i)
        }
        const [o, r, a] = x(e, i, s), l = D(t), c = l[a] || (l[a] = {}),
            h = I(c, r, o ? i : null);
        if (h) return void (h.oneOff = h.oneOff && n);
        const d = N(r, e.replace(E, "")), u = o ? function (t, e, i) {
            return function s(n) {
                const o = t.querySelectorAll(e);
                for (let {target: r} = n; r && r !== this; r = r.parentNode) for (let a = o.length; a--;) if (o[a] === r) return n.delegateTarget = r, s.oneOff && H.off(t, n.type, e, i), i.apply(r, [n]);
                return null
            }
        }(t, i, s) : function (t, e) {
            return function i(s) {
                return s.delegateTarget = t, i.oneOff && H.off(t, s.type, e), e.apply(t, [s])
            }
        }(t, i);
        u.delegationSelector = o ? i : null, u.originalHandler = r, u.oneOff = n, u.uidEvent = d, c[d] = u, t.addEventListener(a, u, o)
    }

    function M(t, e, i, s, n) {
        const o = I(e[i], s, n);
        o && (t.removeEventListener(i, o, Boolean(n)), delete e[i][o.uidEvent])
    }

    function j(t) {
        return t = t.replace(A, ""), L[t] || t
    }

    const H = {
        on(t, e, i, s) {
            P(t, e, i, s, !1)
        }, one(t, e, i, s) {
            P(t, e, i, s, !0)
        }, off(t, e, i, s) {
            if ("string" != typeof e || !t) return;
            const [n, o, r] = x(e, i, s), a = r !== e, l = D(t),
                c = e.startsWith(".");
            if (void 0 !== o) {
                if (!l || !l[r]) return;
                return void M(t, l, r, o, n ? i : null)
            }
            c && Object.keys(l).forEach(i => {
                !function (t, e, i, s) {
                    const n = e[i] || {};
                    Object.keys(n).forEach(o => {
                        if (o.includes(s)) {
                            const s = n[o];
                            M(t, e, i, s.originalHandler, s.delegationSelector)
                        }
                    })
                }(t, l, i, e.slice(1))
            });
            const h = l[r] || {};
            Object.keys(h).forEach(i => {
                const s = i.replace(T, "");
                if (!a || e.includes(s)) {
                    const e = h[i];
                    M(t, l, r, e.originalHandler, e.delegationSelector)
                }
            })
        }, trigger(t, e, i) {
            if ("string" != typeof e || !t) return null;
            const s = f(), n = j(e), o = e !== n, r = O.has(n);
            let a, l = !0, c = !0, h = !1, d = null;
            return o && s && (a = s.Event(e, i), s(t).trigger(a), l = !a.isPropagationStopped(), c = !a.isImmediatePropagationStopped(), h = a.isDefaultPrevented()), r ? (d = document.createEvent("HTMLEvents"), d.initEvent(n, l, !0)) : d = new CustomEvent(e, {
                bubbles: l,
                cancelable: !0
            }), void 0 !== i && Object.keys(i).forEach(t => {
                Object.defineProperty(d, t, {get: () => i[t]})
            }), h && d.preventDefault(), c && t.dispatchEvent(d), d.defaultPrevented && void 0 !== a && a.preventDefault(), d
        }
    }, B = new Map;
    var z = {
        set(t, e, i) {
            B.has(t) || B.set(t, new Map);
            const s = B.get(t);
            s.has(e) || 0 === s.size ? s.set(e, i) : console.error(`Bootstrap doesn't allow more than one instance per element. Bound instance: ${Array.from(s.keys())[0]}.`)
        }, get: (t, e) => B.has(t) && B.get(t).get(e) || null, remove(t, e) {
            if (!B.has(t)) return;
            const i = B.get(t);
            i.delete(e), 0 === i.size && B.delete(t)
        }
    };

    class R {
        constructor(t) {
            (t = l(t)) && (this._element = t, z.set(this._element, this.constructor.DATA_KEY, this))
        }

        dispose() {
            z.remove(this._element, this.constructor.DATA_KEY), H.off(this._element, this.constructor.EVENT_KEY), Object.getOwnPropertyNames(this).forEach(t => {
                this[t] = null
            })
        }

        _queueCallback(t, e, i = !0) {
            y(t, e, i)
        }

        static getInstance(t) {
            return z.get(l(t), this.DATA_KEY)
        }

        static getOrCreateInstance(t, e = {}) {
            return this.getInstance(t) || new this(t, "object" == typeof e ? e : null)
        }

        static get VERSION() {
            return "5.1.0"
        }

        static get NAME() {
            throw new Error('You have to implement the static method "NAME", for each component!')
        }

        static get DATA_KEY() {
            return "bs." + this.NAME
        }

        static get EVENT_KEY() {
            return "." + this.DATA_KEY
        }
    }

    const F = (t, e = "hide") => {
        const i = "click.dismiss" + t.EVENT_KEY, s = t.NAME;
        H.on(document, i, `[data-bs-dismiss="${s}"]`, (function (i) {
            if (["A", "AREA"].includes(this.tagName) && i.preventDefault(), d(this)) return;
            const n = o(this) || this.closest("." + s);
            t.getOrCreateInstance(n)[e]()
        }))
    };

    class W extends R {
        static get NAME() {
            return "alert"
        }

        close() {
            if (H.trigger(this._element, "close.bs.alert").defaultPrevented) return;
            this._element.classList.remove("show");
            const t = this._element.classList.contains("fade");
            this._queueCallback(() => this._destroyElement(), this._element, t)
        }

        _destroyElement() {
            this._element.remove(), H.trigger(this._element, "closed.bs.alert"), this.dispose()
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = W.getOrCreateInstance(this);
                if ("string" == typeof t) {
                    if (void 0 === e[t] || t.startsWith("_") || "constructor" === t) throw new TypeError(`No method named "${t}"`);
                    e[t](this)
                }
            }))
        }
    }

    F(W, "close"), b(W);

    class $ extends R {
        static get NAME() {
            return "button"
        }

        toggle() {
            this._element.setAttribute("aria-pressed", this._element.classList.toggle("active"))
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = $.getOrCreateInstance(this);
                "toggle" === t && e[t]()
            }))
        }
    }

    function q(t) {
        return "true" === t || "false" !== t && (t === Number(t).toString() ? Number(t) : "" === t || "null" === t ? null : t)
    }

    function U(t) {
        return t.replace(/[A-Z]/g, t => "-" + t.toLowerCase())
    }

    H.on(document, "click.bs.button.data-api", '[data-bs-toggle="button"]', t => {
        t.preventDefault();
        const e = t.target.closest('[data-bs-toggle="button"]');
        $.getOrCreateInstance(e).toggle()
    }), b($);
    const K = {
            setDataAttribute(t, e, i) {
                t.setAttribute("data-bs-" + U(e), i)
            },
            removeDataAttribute(t, e) {
                t.removeAttribute("data-bs-" + U(e))
            },
            getDataAttributes(t) {
                if (!t) return {};
                const e = {};
                return Object.keys(t.dataset).filter(t => t.startsWith("bs")).forEach(i => {
                    let s = i.replace(/^bs/, "");
                    s = s.charAt(0).toLowerCase() + s.slice(1, s.length), e[s] = q(t.dataset[i])
                }), e
            },
            getDataAttribute: (t, e) => q(t.getAttribute("data-bs-" + U(e))),
            offset(t) {
                const e = t.getBoundingClientRect();
                return {
                    top: e.top + window.pageYOffset,
                    left: e.left + window.pageXOffset
                }
            },
            position: t => ({top: t.offsetTop, left: t.offsetLeft})
        }, V = {
            find: (t, e = document.documentElement) => [].concat(...Element.prototype.querySelectorAll.call(e, t)),
            findOne: (t, e = document.documentElement) => Element.prototype.querySelector.call(e, t),
            children: (t, e) => [].concat(...t.children).filter(t => t.matches(e)),
            parents(t, e) {
                const i = [];
                let s = t.parentNode;
                for (; s && s.nodeType === Node.ELEMENT_NODE && 3 !== s.nodeType;) s.matches(e) && i.push(s), s = s.parentNode;
                return i
            },
            prev(t, e) {
                let i = t.previousElementSibling;
                for (; i;) {
                    if (i.matches(e)) return [i];
                    i = i.previousElementSibling
                }
                return []
            },
            next(t, e) {
                let i = t.nextElementSibling;
                for (; i;) {
                    if (i.matches(e)) return [i];
                    i = i.nextElementSibling
                }
                return []
            },
            focusableChildren(t) {
                const e = ["a", "button", "input", "textarea", "select", "details", "[tabindex]", '[contenteditable="true"]'].map(t => t + ':not([tabindex^="-"])').join(", ");
                return this.find(e, t).filter(t => !d(t) && h(t))
            }
        }, X = {
            interval: 5e3,
            keyboard: !0,
            slide: !1,
            pause: "hover",
            wrap: !0,
            touch: !0
        }, Y = {
            interval: "(number|boolean)",
            keyboard: "boolean",
            slide: "(boolean|string)",
            pause: "(string|boolean)",
            wrap: "boolean",
            touch: "boolean"
        }, Q = "next", G = "prev", Z = "left", J = "right",
        tt = {ArrowLeft: J, ArrowRight: Z};

    class et extends R {
        constructor(t, e) {
            super(t), this._items = null, this._interval = null, this._activeElement = null, this._isPaused = !1, this._isSliding = !1, this.touchTimeout = null, this.touchStartX = 0, this.touchDeltaX = 0, this._config = this._getConfig(e), this._indicatorsElement = V.findOne(".carousel-indicators", this._element), this._touchSupported = "ontouchstart" in document.documentElement || navigator.maxTouchPoints > 0, this._pointerEvent = Boolean(window.PointerEvent), this._addEventListeners()
        }

        static get Default() {
            return X
        }

        static get NAME() {
            return "carousel"
        }

        next() {
            this._slide(Q)
        }

        nextWhenVisible() {
            !document.hidden && h(this._element) && this.next()
        }

        prev() {
            this._slide(G)
        }

        pause(t) {
            t || (this._isPaused = !0), V.findOne(".carousel-item-next, .carousel-item-prev", this._element) && (r(this._element), this.cycle(!0)), clearInterval(this._interval), this._interval = null
        }

        cycle(t) {
            t || (this._isPaused = !1), this._interval && (clearInterval(this._interval), this._interval = null), this._config && this._config.interval && !this._isPaused && (this._updateInterval(), this._interval = setInterval((document.visibilityState ? this.nextWhenVisible : this.next).bind(this), this._config.interval))
        }

        to(t) {
            this._activeElement = V.findOne(".active.carousel-item", this._element);
            const e = this._getItemIndex(this._activeElement);
            if (t > this._items.length - 1 || t < 0) return;
            if (this._isSliding) return void H.one(this._element, "slid.bs.carousel", () => this.to(t));
            if (e === t) return this.pause(), void this.cycle();
            const i = t > e ? Q : G;
            this._slide(i, this._items[t])
        }

        _getConfig(t) {
            return t = {...X, ...K.getDataAttributes(this._element), ..."object" == typeof t ? t : {}}, c("carousel", t, Y), t
        }

        _handleSwipe() {
            const t = Math.abs(this.touchDeltaX);
            if (t <= 40) return;
            const e = t / this.touchDeltaX;
            this.touchDeltaX = 0, e && this._slide(e > 0 ? J : Z)
        }

        _addEventListeners() {
            this._config.keyboard && H.on(this._element, "keydown.bs.carousel", t => this._keydown(t)), "hover" === this._config.pause && (H.on(this._element, "mouseenter.bs.carousel", t => this.pause(t)), H.on(this._element, "mouseleave.bs.carousel", t => this.cycle(t))), this._config.touch && this._touchSupported && this._addTouchEventListeners()
        }

        _addTouchEventListeners() {
            const t = t => {
                !this._pointerEvent || "pen" !== t.pointerType && "touch" !== t.pointerType ? this._pointerEvent || (this.touchStartX = t.touches[0].clientX) : this.touchStartX = t.clientX
            }, e = t => {
                this.touchDeltaX = t.touches && t.touches.length > 1 ? 0 : t.touches[0].clientX - this.touchStartX
            }, i = t => {
                !this._pointerEvent || "pen" !== t.pointerType && "touch" !== t.pointerType || (this.touchDeltaX = t.clientX - this.touchStartX), this._handleSwipe(), "hover" === this._config.pause && (this.pause(), this.touchTimeout && clearTimeout(this.touchTimeout), this.touchTimeout = setTimeout(t => this.cycle(t), 500 + this._config.interval))
            };
            V.find(".carousel-item img", this._element).forEach(t => {
                H.on(t, "dragstart.bs.carousel", t => t.preventDefault())
            }), this._pointerEvent ? (H.on(this._element, "pointerdown.bs.carousel", e => t(e)), H.on(this._element, "pointerup.bs.carousel", t => i(t)), this._element.classList.add("pointer-event")) : (H.on(this._element, "touchstart.bs.carousel", e => t(e)), H.on(this._element, "touchmove.bs.carousel", t => e(t)), H.on(this._element, "touchend.bs.carousel", t => i(t)))
        }

        _keydown(t) {
            if (/input|textarea/i.test(t.target.tagName)) return;
            const e = tt[t.key];
            e && (t.preventDefault(), this._slide(e))
        }

        _getItemIndex(t) {
            return this._items = t && t.parentNode ? V.find(".carousel-item", t.parentNode) : [], this._items.indexOf(t)
        }

        _getItemByOrder(t, e) {
            const i = t === Q;
            return w(this._items, e, i, this._config.wrap)
        }

        _triggerSlideEvent(t, e) {
            const i = this._getItemIndex(t),
                s = this._getItemIndex(V.findOne(".active.carousel-item", this._element));
            return H.trigger(this._element, "slide.bs.carousel", {
                relatedTarget: t,
                direction: e,
                from: s,
                to: i
            })
        }

        _setActiveIndicatorElement(t) {
            if (this._indicatorsElement) {
                const e = V.findOne(".active", this._indicatorsElement);
                e.classList.remove("active"), e.removeAttribute("aria-current");
                const i = V.find("[data-bs-target]", this._indicatorsElement);
                for (let e = 0; e < i.length; e++) if (Number.parseInt(i[e].getAttribute("data-bs-slide-to"), 10) === this._getItemIndex(t)) {
                    i[e].classList.add("active"), i[e].setAttribute("aria-current", "true");
                    break
                }
            }
        }

        _updateInterval() {
            const t = this._activeElement || V.findOne(".active.carousel-item", this._element);
            if (!t) return;
            const e = Number.parseInt(t.getAttribute("data-bs-interval"), 10);
            e ? (this._config.defaultInterval = this._config.defaultInterval || this._config.interval, this._config.interval = e) : this._config.interval = this._config.defaultInterval || this._config.interval
        }

        _slide(t, e) {
            const i = this._directionToOrder(t),
                s = V.findOne(".active.carousel-item", this._element),
                n = this._getItemIndex(s), o = e || this._getItemByOrder(i, s),
                r = this._getItemIndex(o), a = Boolean(this._interval),
                l = i === Q,
                c = l ? "carousel-item-start" : "carousel-item-end",
                h = l ? "carousel-item-next" : "carousel-item-prev",
                d = this._orderToDirection(i);
            if (o && o.classList.contains("active")) return void (this._isSliding = !1);
            if (this._isSliding) return;
            if (this._triggerSlideEvent(o, d).defaultPrevented) return;
            if (!s || !o) return;
            this._isSliding = !0, a && this.pause(), this._setActiveIndicatorElement(o), this._activeElement = o;
            const u = () => {
                H.trigger(this._element, "slid.bs.carousel", {
                    relatedTarget: o,
                    direction: d,
                    from: n,
                    to: r
                })
            };
            if (this._element.classList.contains("slide")) {
                o.classList.add(h), p(o), s.classList.add(c), o.classList.add(c);
                const t = () => {
                    o.classList.remove(c, h), o.classList.add("active"), s.classList.remove("active", h, c), this._isSliding = !1, setTimeout(u, 0)
                };
                this._queueCallback(t, s, !0)
            } else s.classList.remove("active"), o.classList.add("active"), this._isSliding = !1, u();
            a && this.cycle()
        }

        _directionToOrder(t) {
            return [J, Z].includes(t) ? m() ? t === Z ? G : Q : t === Z ? Q : G : t
        }

        _orderToDirection(t) {
            return [Q, G].includes(t) ? m() ? t === G ? Z : J : t === G ? J : Z : t
        }

        static carouselInterface(t, e) {
            const i = et.getOrCreateInstance(t, e);
            let {_config: s} = i;
            "object" == typeof e && (s = {...s, ...e});
            const n = "string" == typeof e ? e : s.slide;
            if ("number" == typeof e) i.to(e); else if ("string" == typeof n) {
                if (void 0 === i[n]) throw new TypeError(`No method named "${n}"`);
                i[n]()
            } else s.interval && s.ride && (i.pause(), i.cycle())
        }

        static jQueryInterface(t) {
            return this.each((function () {
                et.carouselInterface(this, t)
            }))
        }

        static dataApiClickHandler(t) {
            const e = o(this);
            if (!e || !e.classList.contains("carousel")) return;
            const i = {...K.getDataAttributes(e), ...K.getDataAttributes(this)},
                s = this.getAttribute("data-bs-slide-to");
            s && (i.interval = !1), et.carouselInterface(e, i), s && et.getInstance(e).to(s), t.preventDefault()
        }
    }

    H.on(document, "click.bs.carousel.data-api", "[data-bs-slide], [data-bs-slide-to]", et.dataApiClickHandler), H.on(window, "load.bs.carousel.data-api", () => {
        const t = V.find('[data-bs-ride="carousel"]');
        for (let e = 0, i = t.length; e < i; e++) et.carouselInterface(t[e], et.getInstance(t[e]))
    }), b(et);
    const it = {toggle: !0, parent: null},
        st = {toggle: "boolean", parent: "(null|element)"};

    class nt extends R {
        constructor(t, e) {
            super(t), this._isTransitioning = !1, this._config = this._getConfig(e), this._triggerArray = [];
            const i = V.find('[data-bs-toggle="collapse"]');
            for (let t = 0, e = i.length; t < e; t++) {
                const e = i[t], s = n(e),
                    o = V.find(s).filter(t => t === this._element);
                null !== s && o.length && (this._selector = s, this._triggerArray.push(e))
            }
            this._initializeChildren(), this._config.parent || this._addAriaAndCollapsedClass(this._triggerArray, this._isShown()), this._config.toggle && this.toggle()
        }

        static get Default() {
            return it
        }

        static get NAME() {
            return "collapse"
        }

        toggle() {
            this._isShown() ? this.hide() : this.show()
        }

        show() {
            if (this._isTransitioning || this._isShown()) return;
            let t, e = [];
            if (this._config.parent) {
                const t = V.find(".collapse .collapse", this._config.parent);
                e = V.find(".show, .collapsing", this._config.parent).filter(e => !t.includes(e))
            }
            const i = V.findOne(this._selector);
            if (e.length) {
                const s = e.find(t => i !== t);
                if (t = s ? nt.getInstance(s) : null, t && t._isTransitioning) return
            }
            if (H.trigger(this._element, "show.bs.collapse").defaultPrevented) return;
            e.forEach(e => {
                i !== e && nt.getOrCreateInstance(e, {toggle: !1}).hide(), t || z.set(e, "bs.collapse", null)
            });
            const s = this._getDimension();
            this._element.classList.remove("collapse"), this._element.classList.add("collapsing"), this._element.style[s] = 0, this._addAriaAndCollapsedClass(this._triggerArray, !0), this._isTransitioning = !0;
            const n = "scroll" + (s[0].toUpperCase() + s.slice(1));
            this._queueCallback(() => {
                this._isTransitioning = !1, this._element.classList.remove("collapsing"), this._element.classList.add("collapse", "show"), this._element.style[s] = "", H.trigger(this._element, "shown.bs.collapse")
            }, this._element, !0), this._element.style[s] = this._element[n] + "px"
        }

        hide() {
            if (this._isTransitioning || !this._isShown()) return;
            if (H.trigger(this._element, "hide.bs.collapse").defaultPrevented) return;
            const t = this._getDimension();
            this._element.style[t] = this._element.getBoundingClientRect()[t] + "px", p(this._element), this._element.classList.add("collapsing"), this._element.classList.remove("collapse", "show");
            const e = this._triggerArray.length;
            for (let t = 0; t < e; t++) {
                const e = this._triggerArray[t], i = o(e);
                i && !this._isShown(i) && this._addAriaAndCollapsedClass([e], !1)
            }
            this._isTransitioning = !0, this._element.style[t] = "", this._queueCallback(() => {
                this._isTransitioning = !1, this._element.classList.remove("collapsing"), this._element.classList.add("collapse"), H.trigger(this._element, "hidden.bs.collapse")
            }, this._element, !0)
        }

        _isShown(t = this._element) {
            return t.classList.contains("show")
        }

        _getConfig(t) {
            return (t = {...it, ...K.getDataAttributes(this._element), ...t}).toggle = Boolean(t.toggle), t.parent = l(t.parent), c("collapse", t, st), t
        }

        _getDimension() {
            return this._element.classList.contains("collapse-horizontal") ? "width" : "height"
        }

        _initializeChildren() {
            if (!this._config.parent) return;
            const t = V.find(".collapse .collapse", this._config.parent);
            V.find('[data-bs-toggle="collapse"]', this._config.parent).filter(e => !t.includes(e)).forEach(t => {
                const e = o(t);
                e && this._addAriaAndCollapsedClass([t], this._isShown(e))
            })
        }

        _addAriaAndCollapsedClass(t, e) {
            t.length && t.forEach(t => {
                e ? t.classList.remove("collapsed") : t.classList.add("collapsed"), t.setAttribute("aria-expanded", e)
            })
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = {};
                "string" == typeof t && /show|hide/.test(t) && (e.toggle = !1);
                const i = nt.getOrCreateInstance(this, e);
                if ("string" == typeof t) {
                    if (void 0 === i[t]) throw new TypeError(`No method named "${t}"`);
                    i[t]()
                }
            }))
        }
    }

    H.on(document, "click.bs.collapse.data-api", '[data-bs-toggle="collapse"]', (function (t) {
        ("A" === t.target.tagName || t.delegateTarget && "A" === t.delegateTarget.tagName) && t.preventDefault();
        const e = n(this);
        V.find(e).forEach(t => {
            nt.getOrCreateInstance(t, {toggle: !1}).toggle()
        })
    })), b(nt);
    const ot = new RegExp("ArrowUp|ArrowDown|Escape"),
        rt = m() ? "top-end" : "top-start", at = m() ? "top-start" : "top-end",
        lt = m() ? "bottom-end" : "bottom-start",
        ct = m() ? "bottom-start" : "bottom-end",
        ht = m() ? "left-start" : "right-start",
        dt = m() ? "right-start" : "left-start", ut = {
            offset: [0, 2],
            boundary: "clippingParents",
            reference: "toggle",
            display: "dynamic",
            popperConfig: null,
            autoClose: !0
        }, gt = {
            offset: "(array|string|function)",
            boundary: "(string|element)",
            reference: "(string|element|object)",
            display: "string",
            popperConfig: "(null|object|function)",
            autoClose: "(boolean|string)"
        };

    class pt extends R {
        constructor(t, e) {
            super(t), this._popper = null, this._config = this._getConfig(e), this._menu = this._getMenuElement(), this._inNavbar = this._detectNavbar()
        }

        static get Default() {
            return ut
        }

        static get DefaultType() {
            return gt
        }

        static get NAME() {
            return "dropdown"
        }

        toggle() {
            return this._isShown() ? this.hide() : this.show()
        }

        show() {
            if (d(this._element) || this._isShown(this._menu)) return;
            const t = {relatedTarget: this._element};
            if (H.trigger(this._element, "show.bs.dropdown", t).defaultPrevented) return;
            const e = pt.getParentFromElement(this._element);
            this._inNavbar ? K.setDataAttribute(this._menu, "popper", "none") : this._createPopper(e), "ontouchstart" in document.documentElement && !e.closest(".navbar-nav") && [].concat(...document.body.children).forEach(t => H.on(t, "mouseover", g)), this._element.focus(), this._element.setAttribute("aria-expanded", !0), this._menu.classList.add("show"), this._element.classList.add("show"), H.trigger(this._element, "shown.bs.dropdown", t)
        }

        hide() {
            if (d(this._element) || !this._isShown(this._menu)) return;
            const t = {relatedTarget: this._element};
            this._completeHide(t)
        }

        dispose() {
            this._popper && this._popper.destroy(), super.dispose()
        }

        update() {
            this._inNavbar = this._detectNavbar(), this._popper && this._popper.update()
        }

        _completeHide(t) {
            H.trigger(this._element, "hide.bs.dropdown", t).defaultPrevented || ("ontouchstart" in document.documentElement && [].concat(...document.body.children).forEach(t => H.off(t, "mouseover", g)), this._popper && this._popper.destroy(), this._menu.classList.remove("show"), this._element.classList.remove("show"), this._element.setAttribute("aria-expanded", "false"), K.removeDataAttribute(this._menu, "popper"), H.trigger(this._element, "hidden.bs.dropdown", t))
        }

        _getConfig(t) {
            if (t = {...this.constructor.Default, ...K.getDataAttributes(this._element), ...t}, c("dropdown", t, this.constructor.DefaultType), "object" == typeof t.reference && !a(t.reference) && "function" != typeof t.reference.getBoundingClientRect) throw new TypeError("dropdown".toUpperCase() + ': Option "reference" provided type "object" without a required "getBoundingClientRect" method.');
            return t
        }

        _createPopper(t) {
            if (void 0 === i) throw new TypeError("Bootstrap's dropdowns require Popper (https://popper.js.org)");
            let e = this._element;
            "parent" === this._config.reference ? e = t : a(this._config.reference) ? e = l(this._config.reference) : "object" == typeof this._config.reference && (e = this._config.reference);
            const s = this._getPopperConfig(),
                n = s.modifiers.find(t => "applyStyles" === t.name && !1 === t.enabled);
            this._popper = i.createPopper(e, this._menu, s), n && K.setDataAttribute(this._menu, "popper", "static")
        }

        _isShown(t = this._element) {
            return t.classList.contains("show")
        }

        _getMenuElement() {
            return V.next(this._element, ".dropdown-menu")[0]
        }

        _getPlacement() {
            const t = this._element.parentNode;
            if (t.classList.contains("dropend")) return ht;
            if (t.classList.contains("dropstart")) return dt;
            const e = "end" === getComputedStyle(this._menu).getPropertyValue("--bs-position").trim();
            return t.classList.contains("dropup") ? e ? at : rt : e ? ct : lt
        }

        _detectNavbar() {
            return null !== this._element.closest(".navbar")
        }

        _getOffset() {
            const {offset: t} = this._config;
            return "string" == typeof t ? t.split(",").map(t => Number.parseInt(t, 10)) : "function" == typeof t ? e => t(e, this._element) : t
        }

        _getPopperConfig() {
            const t = {
                placement: this._getPlacement(),
                modifiers: [{
                    name: "preventOverflow",
                    options: {boundary: this._config.boundary}
                }, {name: "offset", options: {offset: this._getOffset()}}]
            };
            return "static" === this._config.display && (t.modifiers = [{
                name: "applyStyles",
                enabled: !1
            }]), {...t, ..."function" == typeof this._config.popperConfig ? this._config.popperConfig(t) : this._config.popperConfig}
        }

        _selectMenuItem({key: t, target: e}) {
            const i = V.find(".dropdown-menu .dropdown-item:not(.disabled):not(:disabled)", this._menu).filter(h);
            i.length && w(i, e, "ArrowDown" === t, !i.includes(e)).focus()
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = pt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t]()
                }
            }))
        }

        static clearMenus(t) {
            if (t && (2 === t.button || "keyup" === t.type && "Tab" !== t.key)) return;
            const e = V.find('[data-bs-toggle="dropdown"]');
            for (let i = 0, s = e.length; i < s; i++) {
                const s = pt.getInstance(e[i]);
                if (!s || !1 === s._config.autoClose) continue;
                if (!s._isShown()) continue;
                const n = {relatedTarget: s._element};
                if (t) {
                    const e = t.composedPath(), i = e.includes(s._menu);
                    if (e.includes(s._element) || "inside" === s._config.autoClose && !i || "outside" === s._config.autoClose && i) continue;
                    if (s._menu.contains(t.target) && ("keyup" === t.type && "Tab" === t.key || /input|select|option|textarea|form/i.test(t.target.tagName))) continue;
                    "click" === t.type && (n.clickEvent = t)
                }
                s._completeHide(n)
            }
        }

        static getParentFromElement(t) {
            return o(t) || t.parentNode
        }

        static dataApiKeydownHandler(t) {
            if (/input|textarea/i.test(t.target.tagName) ? "Space" === t.key || "Escape" !== t.key && ("ArrowDown" !== t.key && "ArrowUp" !== t.key || t.target.closest(".dropdown-menu")) : !ot.test(t.key)) return;
            const e = this.classList.contains("show");
            if (!e && "Escape" === t.key) return;
            if (t.preventDefault(), t.stopPropagation(), d(this)) return;
            const i = this.matches('[data-bs-toggle="dropdown"]') ? this : V.prev(this, '[data-bs-toggle="dropdown"]')[0],
                s = pt.getOrCreateInstance(i);
            if ("Escape" !== t.key) return "ArrowUp" === t.key || "ArrowDown" === t.key ? (e || s.show(), void s._selectMenuItem(t)) : void (e && "Space" !== t.key || pt.clearMenus());
            s.hide()
        }
    }

    H.on(document, "keydown.bs.dropdown.data-api", '[data-bs-toggle="dropdown"]', pt.dataApiKeydownHandler), H.on(document, "keydown.bs.dropdown.data-api", ".dropdown-menu", pt.dataApiKeydownHandler), H.on(document, "click.bs.dropdown.data-api", pt.clearMenus), H.on(document, "keyup.bs.dropdown.data-api", pt.clearMenus), H.on(document, "click.bs.dropdown.data-api", '[data-bs-toggle="dropdown"]', (function (t) {
        t.preventDefault(), pt.getOrCreateInstance(this).toggle()
    })), b(pt);

    class ft {
        constructor() {
            this._element = document.body
        }

        getWidth() {
            const t = document.documentElement.clientWidth;
            return Math.abs(window.innerWidth - t)
        }

        hide() {
            const t = this.getWidth();
            this._disableOverFlow(), this._setElementAttributes(this._element, "paddingRight", e => e + t), this._setElementAttributes(".fixed-top, .fixed-bottom, .is-fixed, .sticky-top", "paddingRight", e => e + t), this._setElementAttributes(".sticky-top", "marginRight", e => e - t)
        }

        _disableOverFlow() {
            this._saveInitialAttribute(this._element, "overflow"), this._element.style.overflow = "hidden"
        }

        _setElementAttributes(t, e, i) {
            const s = this.getWidth();
            this._applyManipulationCallback(t, t => {
                if (t !== this._element && window.innerWidth > t.clientWidth + s) return;
                this._saveInitialAttribute(t, e);
                const n = window.getComputedStyle(t)[e];
                t.style[e] = i(Number.parseFloat(n)) + "px"
            })
        }

        reset() {
            this._resetElementAttributes(this._element, "overflow"), this._resetElementAttributes(this._element, "paddingRight"), this._resetElementAttributes(".fixed-top, .fixed-bottom, .is-fixed, .sticky-top", "paddingRight"), this._resetElementAttributes(".sticky-top", "marginRight")
        }

        _saveInitialAttribute(t, e) {
            const i = t.style[e];
            i && K.setDataAttribute(t, e, i)
        }

        _resetElementAttributes(t, e) {
            this._applyManipulationCallback(t, t => {
                const i = K.getDataAttribute(t, e);
                void 0 === i ? t.style.removeProperty(e) : (K.removeDataAttribute(t, e), t.style[e] = i)
            })
        }

        _applyManipulationCallback(t, e) {
            a(t) ? e(t) : V.find(t, this._element).forEach(e)
        }

        isOverflowing() {
            return this.getWidth() > 0
        }
    }

    const _t = {
        className: "modal-backdrop",
        isVisible: !0,
        isAnimated: !1,
        rootElement: "body",
        clickCallback: null
    }, mt = {
        className: "string",
        isVisible: "boolean",
        isAnimated: "boolean",
        rootElement: "(element|string)",
        clickCallback: "(function|null)"
    };

    class bt {
        constructor(t) {
            this._config = this._getConfig(t), this._isAppended = !1, this._element = null
        }

        show(t) {
            this._config.isVisible ? (this._append(), this._config.isAnimated && p(this._getElement()), this._getElement().classList.add("show"), this._emulateAnimation(() => {
                v(t)
            })) : v(t)
        }

        hide(t) {
            this._config.isVisible ? (this._getElement().classList.remove("show"), this._emulateAnimation(() => {
                this.dispose(), v(t)
            })) : v(t)
        }

        _getElement() {
            if (!this._element) {
                const t = document.createElement("div");
                t.className = this._config.className, this._config.isAnimated && t.classList.add("fade"), this._element = t
            }
            return this._element
        }

        _getConfig(t) {
            return (t = {..._t, ..."object" == typeof t ? t : {}}).rootElement = l(t.rootElement), c("backdrop", t, mt), t
        }

        _append() {
            this._isAppended || (this._config.rootElement.append(this._getElement()), H.on(this._getElement(), "mousedown.bs.backdrop", () => {
                v(this._config.clickCallback)
            }), this._isAppended = !0)
        }

        dispose() {
            this._isAppended && (H.off(this._element, "mousedown.bs.backdrop"), this._element.remove(), this._isAppended = !1)
        }

        _emulateAnimation(t) {
            y(t, this._getElement(), this._config.isAnimated)
        }
    }

    const vt = {trapElement: null, autofocus: !0},
        yt = {trapElement: "element", autofocus: "boolean"};

    class wt {
        constructor(t) {
            this._config = this._getConfig(t), this._isActive = !1, this._lastTabNavDirection = null
        }

        activate() {
            const {trapElement: t, autofocus: e} = this._config;
            this._isActive || (e && t.focus(), H.off(document, ".bs.focustrap"), H.on(document, "focusin.bs.focustrap", t => this._handleFocusin(t)), H.on(document, "keydown.tab.bs.focustrap", t => this._handleKeydown(t)), this._isActive = !0)
        }

        deactivate() {
            this._isActive && (this._isActive = !1, H.off(document, ".bs.focustrap"))
        }

        _handleFocusin(t) {
            const {target: e} = t, {trapElement: i} = this._config;
            if (e === document || e === i || i.contains(e)) return;
            const s = V.focusableChildren(i);
            0 === s.length ? i.focus() : "backward" === this._lastTabNavDirection ? s[s.length - 1].focus() : s[0].focus()
        }

        _handleKeydown(t) {
            "Tab" === t.key && (this._lastTabNavDirection = t.shiftKey ? "backward" : "forward")
        }

        _getConfig(t) {
            return t = {...vt, ..."object" == typeof t ? t : {}}, c("focustrap", t, yt), t
        }
    }

    const Et = {backdrop: !0, keyboard: !0, focus: !0}, At = {
        backdrop: "(boolean|string)",
        keyboard: "boolean",
        focus: "boolean"
    };

    class Tt extends R {
        constructor(t, e) {
            super(t), this._config = this._getConfig(e), this._dialog = V.findOne(".modal-dialog", this._element), this._backdrop = this._initializeBackDrop(), this._focustrap = this._initializeFocusTrap(), this._isShown = !1, this._ignoreBackdropClick = !1, this._isTransitioning = !1, this._scrollBar = new ft
        }

        static get Default() {
            return Et
        }

        static get NAME() {
            return "modal"
        }

        toggle(t) {
            return this._isShown ? this.hide() : this.show(t)
        }

        show(t) {
            this._isShown || this._isTransitioning || H.trigger(this._element, "show.bs.modal", {relatedTarget: t}).defaultPrevented || (this._isShown = !0, this._isAnimated() && (this._isTransitioning = !0), this._scrollBar.hide(), document.body.classList.add("modal-open"), this._adjustDialog(), this._setEscapeEvent(), this._setResizeEvent(), H.on(this._dialog, "mousedown.dismiss.bs.modal", () => {
                H.one(this._element, "mouseup.dismiss.bs.modal", t => {
                    t.target === this._element && (this._ignoreBackdropClick = !0)
                })
            }), this._showBackdrop(() => this._showElement(t)))
        }

        hide() {
            if (!this._isShown || this._isTransitioning) return;
            if (H.trigger(this._element, "hide.bs.modal").defaultPrevented) return;
            this._isShown = !1;
            const t = this._isAnimated();
            t && (this._isTransitioning = !0), this._setEscapeEvent(), this._setResizeEvent(), this._focustrap.deactivate(), this._element.classList.remove("show"), H.off(this._element, "click.dismiss.bs.modal"), H.off(this._dialog, "mousedown.dismiss.bs.modal"), this._queueCallback(() => this._hideModal(), this._element, t)
        }

        dispose() {
            [window, this._dialog].forEach(t => H.off(t, ".bs.modal")), this._backdrop.dispose(), this._focustrap.deactivate(), super.dispose()
        }

        handleUpdate() {
            this._adjustDialog()
        }

        _initializeBackDrop() {
            return new bt({
                isVisible: Boolean(this._config.backdrop),
                isAnimated: this._isAnimated()
            })
        }

        _initializeFocusTrap() {
            return new wt({trapElement: this._element})
        }

        _getConfig(t) {
            return t = {...Et, ...K.getDataAttributes(this._element), ..."object" == typeof t ? t : {}}, c("modal", t, At), t
        }

        _showElement(t) {
            const e = this._isAnimated(),
                i = V.findOne(".modal-body", this._dialog);
            this._element.parentNode && this._element.parentNode.nodeType === Node.ELEMENT_NODE || document.body.append(this._element), this._element.style.display = "block", this._element.removeAttribute("aria-hidden"), this._element.setAttribute("aria-modal", !0), this._element.setAttribute("role", "dialog"), this._element.scrollTop = 0, i && (i.scrollTop = 0), e && p(this._element), this._element.classList.add("show"), this._queueCallback(() => {
                this._config.focus && this._focustrap.activate(), this._isTransitioning = !1, H.trigger(this._element, "shown.bs.modal", {relatedTarget: t})
            }, this._dialog, e)
        }

        _setEscapeEvent() {
            this._isShown ? H.on(this._element, "keydown.dismiss.bs.modal", t => {
                this._config.keyboard && "Escape" === t.key ? (t.preventDefault(), this.hide()) : this._config.keyboard || "Escape" !== t.key || this._triggerBackdropTransition()
            }) : H.off(this._element, "keydown.dismiss.bs.modal")
        }

        _setResizeEvent() {
            this._isShown ? H.on(window, "resize.bs.modal", () => this._adjustDialog()) : H.off(window, "resize.bs.modal")
        }

        _hideModal() {
            this._element.style.display = "none", this._element.setAttribute("aria-hidden", !0), this._element.removeAttribute("aria-modal"), this._element.removeAttribute("role"), this._isTransitioning = !1, this._backdrop.hide(() => {
                document.body.classList.remove("modal-open"), this._resetAdjustments(), this._scrollBar.reset(), H.trigger(this._element, "hidden.bs.modal")
            })
        }

        _showBackdrop(t) {
            H.on(this._element, "click.dismiss.bs.modal", t => {
                this._ignoreBackdropClick ? this._ignoreBackdropClick = !1 : t.target === t.currentTarget && (!0 === this._config.backdrop ? this.hide() : "static" === this._config.backdrop && this._triggerBackdropTransition())
            }), this._backdrop.show(t)
        }

        _isAnimated() {
            return this._element.classList.contains("fade")
        }

        _triggerBackdropTransition() {
            if (H.trigger(this._element, "hidePrevented.bs.modal").defaultPrevented) return;
            const {classList: t, scrollHeight: e, style: i} = this._element,
                s = e > document.documentElement.clientHeight;
            !s && "hidden" === i.overflowY || t.contains("modal-static") || (s || (i.overflowY = "hidden"), t.add("modal-static"), this._queueCallback(() => {
                t.remove("modal-static"), s || this._queueCallback(() => {
                    i.overflowY = ""
                }, this._dialog)
            }, this._dialog), this._element.focus())
        }

        _adjustDialog() {
            const t = this._element.scrollHeight > document.documentElement.clientHeight,
                e = this._scrollBar.getWidth(), i = e > 0;
            (!i && t && !m() || i && !t && m()) && (this._element.style.paddingLeft = e + "px"), (i && !t && !m() || !i && t && m()) && (this._element.style.paddingRight = e + "px")
        }

        _resetAdjustments() {
            this._element.style.paddingLeft = "", this._element.style.paddingRight = ""
        }

        static jQueryInterface(t, e) {
            return this.each((function () {
                const i = Tt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === i[t]) throw new TypeError(`No method named "${t}"`);
                    i[t](e)
                }
            }))
        }
    }

    H.on(document, "click.bs.modal.data-api", '[data-bs-toggle="modal"]', (function (t) {
        const e = o(this);
        ["A", "AREA"].includes(this.tagName) && t.preventDefault(), H.one(e, "show.bs.modal", t => {
            t.defaultPrevented || H.one(e, "hidden.bs.modal", () => {
                h(this) && this.focus()
            })
        }), Tt.getOrCreateInstance(e).toggle(this)
    })), F(Tt), b(Tt);
    const Ct = {backdrop: !0, keyboard: !0, scroll: !1},
        kt = {backdrop: "boolean", keyboard: "boolean", scroll: "boolean"};

    class Lt extends R {
        constructor(t, e) {
            super(t), this._config = this._getConfig(e), this._isShown = !1, this._backdrop = this._initializeBackDrop(), this._focustrap = this._initializeFocusTrap(), this._addEventListeners()
        }

        static get NAME() {
            return "offcanvas"
        }

        static get Default() {
            return Ct
        }

        toggle(t) {
            return this._isShown ? this.hide() : this.show(t)
        }

        show(t) {
            this._isShown || H.trigger(this._element, "show.bs.offcanvas", {relatedTarget: t}).defaultPrevented || (this._isShown = !0, this._element.style.visibility = "visible", this._backdrop.show(), this._config.scroll || (new ft).hide(), this._element.removeAttribute("aria-hidden"), this._element.setAttribute("aria-modal", !0), this._element.setAttribute("role", "dialog"), this._element.classList.add("show"), this._queueCallback(() => {
                this._config.scroll || this._focustrap.activate(), H.trigger(this._element, "shown.bs.offcanvas", {relatedTarget: t})
            }, this._element, !0))
        }

        hide() {
            this._isShown && (H.trigger(this._element, "hide.bs.offcanvas").defaultPrevented || (this._focustrap.deactivate(), this._element.blur(), this._isShown = !1, this._element.classList.remove("show"), this._backdrop.hide(), this._queueCallback(() => {
                this._element.setAttribute("aria-hidden", !0), this._element.removeAttribute("aria-modal"), this._element.removeAttribute("role"), this._element.style.visibility = "hidden", this._config.scroll || (new ft).reset(), H.trigger(this._element, "hidden.bs.offcanvas")
            }, this._element, !0)))
        }

        dispose() {
            this._backdrop.dispose(), this._focustrap.deactivate(), super.dispose()
        }

        _getConfig(t) {
            return t = {...Ct, ...K.getDataAttributes(this._element), ..."object" == typeof t ? t : {}}, c("offcanvas", t, kt), t
        }

        _initializeBackDrop() {
            return new bt({
                className: "offcanvas-backdrop",
                isVisible: this._config.backdrop,
                isAnimated: !0,
                rootElement: this._element.parentNode,
                clickCallback: () => this.hide()
            })
        }

        _initializeFocusTrap() {
            return new wt({trapElement: this._element})
        }

        _addEventListeners() {
            H.on(this._element, "keydown.dismiss.bs.offcanvas", t => {
                this._config.keyboard && "Escape" === t.key && this.hide()
            })
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Lt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t] || t.startsWith("_") || "constructor" === t) throw new TypeError(`No method named "${t}"`);
                    e[t](this)
                }
            }))
        }
    }

    H.on(document, "click.bs.offcanvas.data-api", '[data-bs-toggle="offcanvas"]', (function (t) {
        const e = o(this);
        if (["A", "AREA"].includes(this.tagName) && t.preventDefault(), d(this)) return;
        H.one(e, "hidden.bs.offcanvas", () => {
            h(this) && this.focus()
        });
        const i = V.findOne(".offcanvas.show");
        i && i !== e && Lt.getInstance(i).hide(), Lt.getOrCreateInstance(e).toggle(this)
    })), H.on(window, "load.bs.offcanvas.data-api", () => V.find(".offcanvas.show").forEach(t => Lt.getOrCreateInstance(t).show())), F(Lt), b(Lt);
    const St = new Set(["background", "cite", "href", "itemtype", "longdesc", "poster", "src", "xlink:href"]),
        Ot = /^(?:(?:https?|mailto|ftp|tel|file):|[^#&/:?]*(?:[#/?]|$))/i,
        Nt = /^data:(?:image\/(?:bmp|gif|jpeg|jpg|png|tiff|webp)|video\/(?:mpeg|mp4|ogg|webm)|audio\/(?:mp3|oga|ogg|opus));base64,[\d+/a-z]+=*$/i,
        Dt = (t, e) => {
            const i = t.nodeName.toLowerCase();
            if (e.includes(i)) return !St.has(i) || Boolean(Ot.test(t.nodeValue) || Nt.test(t.nodeValue));
            const s = e.filter(t => t instanceof RegExp);
            for (let t = 0, e = s.length; t < e; t++) if (s[t].test(i)) return !0;
            return !1
        };

    function It(t, e, i) {
        if (!t.length) return t;
        if (i && "function" == typeof i) return i(t);
        const s = (new window.DOMParser).parseFromString(t, "text/html"),
            n = Object.keys(e), o = [].concat(...s.body.querySelectorAll("*"));
        for (let t = 0, i = o.length; t < i; t++) {
            const i = o[t], s = i.nodeName.toLowerCase();
            if (!n.includes(s)) {
                i.remove();
                continue
            }
            const r = [].concat(...i.attributes),
                a = [].concat(e["*"] || [], e[s] || []);
            r.forEach(t => {
                Dt(t, a) || i.removeAttribute(t.nodeName)
            })
        }
        return s.body.innerHTML
    }

    const xt = new Set(["sanitize", "allowList", "sanitizeFn"]), Pt = {
        animation: "boolean",
        template: "string",
        title: "(string|element|function)",
        trigger: "string",
        delay: "(number|object)",
        html: "boolean",
        selector: "(string|boolean)",
        placement: "(string|function)",
        offset: "(array|string|function)",
        container: "(string|element|boolean)",
        fallbackPlacements: "array",
        boundary: "(string|element)",
        customClass: "(string|function)",
        sanitize: "boolean",
        sanitizeFn: "(null|function)",
        allowList: "object",
        popperConfig: "(null|object|function)"
    }, Mt = {
        AUTO: "auto",
        TOP: "top",
        RIGHT: m() ? "left" : "right",
        BOTTOM: "bottom",
        LEFT: m() ? "right" : "left"
    }, jt = {
        animation: !0,
        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
        trigger: "hover focus",
        title: "",
        delay: 0,
        html: !1,
        selector: !1,
        placement: "top",
        offset: [0, 0],
        container: !1,
        fallbackPlacements: ["top", "right", "bottom", "left"],
        boundary: "clippingParents",
        customClass: "",
        sanitize: !0,
        sanitizeFn: null,
        allowList: {
            "*": ["class", "dir", "id", "lang", "role", /^aria-[\w-]*$/i],
            a: ["target", "href", "title", "rel"],
            area: [],
            b: [],
            br: [],
            col: [],
            code: [],
            div: [],
            em: [],
            hr: [],
            h1: [],
            h2: [],
            h3: [],
            h4: [],
            h5: [],
            h6: [],
            i: [],
            img: ["src", "srcset", "alt", "title", "width", "height"],
            li: [],
            ol: [],
            p: [],
            pre: [],
            s: [],
            small: [],
            span: [],
            sub: [],
            sup: [],
            strong: [],
            u: [],
            ul: []
        },
        popperConfig: null
    }, Ht = {
        HIDE: "hide.bs.tooltip",
        HIDDEN: "hidden.bs.tooltip",
        SHOW: "show.bs.tooltip",
        SHOWN: "shown.bs.tooltip",
        INSERTED: "inserted.bs.tooltip",
        CLICK: "click.bs.tooltip",
        FOCUSIN: "focusin.bs.tooltip",
        FOCUSOUT: "focusout.bs.tooltip",
        MOUSEENTER: "mouseenter.bs.tooltip",
        MOUSELEAVE: "mouseleave.bs.tooltip"
    };

    class Bt extends R {
        constructor(t, e) {
            if (void 0 === i) throw new TypeError("Bootstrap's tooltips require Popper (https://popper.js.org)");
            super(t), this._isEnabled = !0, this._timeout = 0, this._hoverState = "", this._activeTrigger = {}, this._popper = null, this._config = this._getConfig(e), this.tip = null, this._setListeners()
        }

        static get Default() {
            return jt
        }

        static get NAME() {
            return "tooltip"
        }

        static get Event() {
            return Ht
        }

        static get DefaultType() {
            return Pt
        }

        enable() {
            this._isEnabled = !0
        }

        disable() {
            this._isEnabled = !1
        }

        toggleEnabled() {
            this._isEnabled = !this._isEnabled
        }

        toggle(t) {
            if (this._isEnabled) if (t) {
                const e = this._initializeOnDelegatedTarget(t);
                e._activeTrigger.click = !e._activeTrigger.click, e._isWithActiveTrigger() ? e._enter(null, e) : e._leave(null, e)
            } else {
                if (this.getTipElement().classList.contains("show")) return void this._leave(null, this);
                this._enter(null, this)
            }
        }

        dispose() {
            clearTimeout(this._timeout), H.off(this._element.closest(".modal"), "hide.bs.modal", this._hideModalHandler), this.tip && this.tip.remove(), this._popper && this._popper.destroy(), super.dispose()
        }

        show() {
            if ("none" === this._element.style.display) throw new Error("Please use show on visible elements");
            if (!this.isWithContent() || !this._isEnabled) return;
            const t = H.trigger(this._element, this.constructor.Event.SHOW),
                e = u(this._element),
                s = null === e ? this._element.ownerDocument.documentElement.contains(this._element) : e.contains(this._element);
            if (t.defaultPrevented || !s) return;
            const n = this.getTipElement(), o = (t => {
                do {
                    t += Math.floor(1e6 * Math.random())
                } while (document.getElementById(t));
                return t
            })(this.constructor.NAME);
            n.setAttribute("id", o), this._element.setAttribute("aria-describedby", o), this._config.animation && n.classList.add("fade");
            const r = "function" == typeof this._config.placement ? this._config.placement.call(this, n, this._element) : this._config.placement,
                a = this._getAttachment(r);
            this._addAttachmentClass(a);
            const {container: l} = this._config;
            z.set(n, this.constructor.DATA_KEY, this), this._element.ownerDocument.documentElement.contains(this.tip) || (l.append(n), H.trigger(this._element, this.constructor.Event.INSERTED)), this._popper ? this._popper.update() : this._popper = i.createPopper(this._element, n, this._getPopperConfig(a)), n.classList.add("show");
            const c = this._resolvePossibleFunction(this._config.customClass);
            c && n.classList.add(...c.split(" ")), "ontouchstart" in document.documentElement && [].concat(...document.body.children).forEach(t => {
                H.on(t, "mouseover", g)
            });
            const h = this.tip.classList.contains("fade");
            this._queueCallback(() => {
                const t = this._hoverState;
                this._hoverState = null, H.trigger(this._element, this.constructor.Event.SHOWN), "out" === t && this._leave(null, this)
            }, this.tip, h)
        }

        hide() {
            if (!this._popper) return;
            const t = this.getTipElement();
            if (H.trigger(this._element, this.constructor.Event.HIDE).defaultPrevented) return;
            t.classList.remove("show"), "ontouchstart" in document.documentElement && [].concat(...document.body.children).forEach(t => H.off(t, "mouseover", g)), this._activeTrigger.click = !1, this._activeTrigger.focus = !1, this._activeTrigger.hover = !1;
            const e = this.tip.classList.contains("fade");
            this._queueCallback(() => {
                this._isWithActiveTrigger() || ("show" !== this._hoverState && t.remove(), this._cleanTipClass(), this._element.removeAttribute("aria-describedby"), H.trigger(this._element, this.constructor.Event.HIDDEN), this._popper && (this._popper.destroy(), this._popper = null))
            }, this.tip, e), this._hoverState = ""
        }

        update() {
            null !== this._popper && this._popper.update()
        }

        isWithContent() {
            return Boolean(this.getTitle())
        }

        getTipElement() {
            if (this.tip) return this.tip;
            const t = document.createElement("div");
            t.innerHTML = this._config.template;
            const e = t.children[0];
            return this.setContent(e), e.classList.remove("fade", "show"), this.tip = e, this.tip
        }

        setContent(t) {
            this._sanitizeAndSetContent(t, this.getTitle(), ".tooltip-inner")
        }

        _sanitizeAndSetContent(t, e, i) {
            const s = V.findOne(i, t);
            e || !s ? this.setElementContent(s, e) : s.remove()
        }

        setElementContent(t, e) {
            if (null !== t) return a(e) ? (e = l(e), void (this._config.html ? e.parentNode !== t && (t.innerHTML = "", t.append(e)) : t.textContent = e.textContent)) : void (this._config.html ? (this._config.sanitize && (e = It(e, this._config.allowList, this._config.sanitizeFn)), t.innerHTML = e) : t.textContent = e)
        }

        getTitle() {
            const t = this._element.getAttribute("data-bs-original-title") || this._config.title;
            return this._resolvePossibleFunction(t)
        }

        updateAttachment(t) {
            return "right" === t ? "end" : "left" === t ? "start" : t
        }

        _initializeOnDelegatedTarget(t, e) {
            return e || this.constructor.getOrCreateInstance(t.delegateTarget, this._getDelegateConfig())
        }

        _getOffset() {
            const {offset: t} = this._config;
            return "string" == typeof t ? t.split(",").map(t => Number.parseInt(t, 10)) : "function" == typeof t ? e => t(e, this._element) : t
        }

        _resolvePossibleFunction(t) {
            return "function" == typeof t ? t.call(this._element) : t
        }

        _getPopperConfig(t) {
            const e = {
                placement: t,
                modifiers: [{
                    name: "flip",
                    options: {fallbackPlacements: this._config.fallbackPlacements}
                }, {
                    name: "offset",
                    options: {offset: this._getOffset()}
                }, {
                    name: "preventOverflow",
                    options: {boundary: this._config.boundary}
                }, {
                    name: "arrow",
                    options: {element: `.${this.constructor.NAME}-arrow`}
                }, {
                    name: "onChange",
                    enabled: !0,
                    phase: "afterWrite",
                    fn: t => this._handlePopperPlacementChange(t)
                }],
                onFirstUpdate: t => {
                    t.options.placement !== t.placement && this._handlePopperPlacementChange(t)
                }
            };
            return {...e, ..."function" == typeof this._config.popperConfig ? this._config.popperConfig(e) : this._config.popperConfig}
        }

        _addAttachmentClass(t) {
            this.getTipElement().classList.add(`${this._getBasicClassPrefix()}-${this.updateAttachment(t)}`)
        }

        _getAttachment(t) {
            return Mt[t.toUpperCase()]
        }

        _setListeners() {
            this._config.trigger.split(" ").forEach(t => {
                if ("click" === t) H.on(this._element, this.constructor.Event.CLICK, this._config.selector, t => this.toggle(t)); else if ("manual" !== t) {
                    const e = "hover" === t ? this.constructor.Event.MOUSEENTER : this.constructor.Event.FOCUSIN,
                        i = "hover" === t ? this.constructor.Event.MOUSELEAVE : this.constructor.Event.FOCUSOUT;
                    H.on(this._element, e, this._config.selector, t => this._enter(t)), H.on(this._element, i, this._config.selector, t => this._leave(t))
                }
            }), this._hideModalHandler = () => {
                this._element && this.hide()
            }, H.on(this._element.closest(".modal"), "hide.bs.modal", this._hideModalHandler), this._config.selector ? this._config = {
                ...this._config,
                trigger: "manual",
                selector: ""
            } : this._fixTitle()
        }

        _fixTitle() {
            const t = this._element.getAttribute("title"),
                e = typeof this._element.getAttribute("data-bs-original-title");
            (t || "string" !== e) && (this._element.setAttribute("data-bs-original-title", t || ""), !t || this._element.getAttribute("aria-label") || this._element.textContent || this._element.setAttribute("aria-label", t), this._element.setAttribute("title", ""))
        }

        _enter(t, e) {
            e = this._initializeOnDelegatedTarget(t, e), t && (e._activeTrigger["focusin" === t.type ? "focus" : "hover"] = !0), e.getTipElement().classList.contains("show") || "show" === e._hoverState ? e._hoverState = "show" : (clearTimeout(e._timeout), e._hoverState = "show", e._config.delay && e._config.delay.show ? e._timeout = setTimeout(() => {
                "show" === e._hoverState && e.show()
            }, e._config.delay.show) : e.show())
        }

        _leave(t, e) {
            e = this._initializeOnDelegatedTarget(t, e), t && (e._activeTrigger["focusout" === t.type ? "focus" : "hover"] = e._element.contains(t.relatedTarget)), e._isWithActiveTrigger() || (clearTimeout(e._timeout), e._hoverState = "out", e._config.delay && e._config.delay.hide ? e._timeout = setTimeout(() => {
                "out" === e._hoverState && e.hide()
            }, e._config.delay.hide) : e.hide())
        }

        _isWithActiveTrigger() {
            for (const t in this._activeTrigger) if (this._activeTrigger[t]) return !0;
            return !1
        }

        _getConfig(t) {
            const e = K.getDataAttributes(this._element);
            return Object.keys(e).forEach(t => {
                xt.has(t) && delete e[t]
            }), (t = {...this.constructor.Default, ...e, ..."object" == typeof t && t ? t : {}}).container = !1 === t.container ? document.body : l(t.container), "number" == typeof t.delay && (t.delay = {
                show: t.delay,
                hide: t.delay
            }), "number" == typeof t.title && (t.title = t.title.toString()), "number" == typeof t.content && (t.content = t.content.toString()), c("tooltip", t, this.constructor.DefaultType), t.sanitize && (t.template = It(t.template, t.allowList, t.sanitizeFn)), t
        }

        _getDelegateConfig() {
            const t = {};
            for (const e in this._config) this.constructor.Default[e] !== this._config[e] && (t[e] = this._config[e]);
            return t
        }

        _cleanTipClass() {
            const t = this.getTipElement(),
                e = new RegExp(`(^|\\s)${this._getBasicClassPrefix()}\\S+`, "g"),
                i = t.getAttribute("class").match(e);
            null !== i && i.length > 0 && i.map(t => t.trim()).forEach(e => t.classList.remove(e))
        }

        _getBasicClassPrefix() {
            return "bs-tooltip"
        }

        _handlePopperPlacementChange(t) {
            const {state: e} = t;
            e && (this.tip = e.elements.popper, this._cleanTipClass(), this._addAttachmentClass(this._getAttachment(e.placement)))
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Bt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t]()
                }
            }))
        }
    }

    b(Bt);
    const zt = {
        ...Bt.Default,
        placement: "right",
        offset: [0, 8],
        trigger: "click",
        content: "",
        template: '<div class="popover" role="tooltip"><div class="popover-arrow"></div><h3 class="popover-header"></h3><div class="popover-body"></div></div>'
    }, Rt = {...Bt.DefaultType, content: "(string|element|function)"}, Ft = {
        HIDE: "hide.bs.popover",
        HIDDEN: "hidden.bs.popover",
        SHOW: "show.bs.popover",
        SHOWN: "shown.bs.popover",
        INSERTED: "inserted.bs.popover",
        CLICK: "click.bs.popover",
        FOCUSIN: "focusin.bs.popover",
        FOCUSOUT: "focusout.bs.popover",
        MOUSEENTER: "mouseenter.bs.popover",
        MOUSELEAVE: "mouseleave.bs.popover"
    };

    class Wt extends Bt {
        static get Default() {
            return zt
        }

        static get NAME() {
            return "popover"
        }

        static get Event() {
            return Ft
        }

        static get DefaultType() {
            return Rt
        }

        isWithContent() {
            return this.getTitle() || this._getContent()
        }

        setContent(t) {
            this._sanitizeAndSetContent(t, this.getTitle(), ".popover-header"), this._sanitizeAndSetContent(t, this._getContent(), ".popover-body")
        }

        _getContent() {
            return this._resolvePossibleFunction(this._config.content)
        }

        _getBasicClassPrefix() {
            return "bs-popover"
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Wt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t]()
                }
            }))
        }
    }

    b(Wt);
    const $t = {offset: 10, method: "auto", target: ""},
        qt = {offset: "number", method: "string", target: "(string|element)"},
        Ut = ".nav-link, .list-group-item, .dropdown-item";

    class Kt extends R {
        constructor(t, e) {
            super(t), this._scrollElement = "BODY" === this._element.tagName ? window : this._element, this._config = this._getConfig(e), this._offsets = [], this._targets = [], this._activeTarget = null, this._scrollHeight = 0, H.on(this._scrollElement, "scroll.bs.scrollspy", () => this._process()), this.refresh(), this._process()
        }

        static get Default() {
            return $t
        }

        static get NAME() {
            return "scrollspy"
        }

        refresh() {
            const t = this._scrollElement === this._scrollElement.window ? "offset" : "position",
                e = "auto" === this._config.method ? t : this._config.method,
                i = "position" === e ? this._getScrollTop() : 0;
            this._offsets = [], this._targets = [], this._scrollHeight = this._getScrollHeight(), V.find(Ut, this._config.target).map(t => {
                const s = n(t), o = s ? V.findOne(s) : null;
                if (o) {
                    const t = o.getBoundingClientRect();
                    if (t.width || t.height) return [K[e](o).top + i, s]
                }
                return null
            }).filter(t => t).sort((t, e) => t[0] - e[0]).forEach(t => {
                this._offsets.push(t[0]), this._targets.push(t[1])
            })
        }

        dispose() {
            H.off(this._scrollElement, ".bs.scrollspy"), super.dispose()
        }

        _getConfig(t) {
            return (t = {...$t, ...K.getDataAttributes(this._element), ..."object" == typeof t && t ? t : {}}).target = l(t.target) || document.documentElement, c("scrollspy", t, qt), t
        }

        _getScrollTop() {
            return this._scrollElement === window ? this._scrollElement.pageYOffset : this._scrollElement.scrollTop
        }

        _getScrollHeight() {
            return this._scrollElement.scrollHeight || Math.max(document.body.scrollHeight, document.documentElement.scrollHeight)
        }

        _getOffsetHeight() {
            return this._scrollElement === window ? window.innerHeight : this._scrollElement.getBoundingClientRect().height
        }

        _process() {
            const t = this._getScrollTop() + this._config.offset,
                e = this._getScrollHeight(),
                i = this._config.offset + e - this._getOffsetHeight();
            if (this._scrollHeight !== e && this.refresh(), t >= i) {
                const t = this._targets[this._targets.length - 1];
                this._activeTarget !== t && this._activate(t)
            } else {
                if (this._activeTarget && t < this._offsets[0] && this._offsets[0] > 0) return this._activeTarget = null, void this._clear();
                for (let e = this._offsets.length; e--;) this._activeTarget !== this._targets[e] && t >= this._offsets[e] && (void 0 === this._offsets[e + 1] || t < this._offsets[e + 1]) && this._activate(this._targets[e])
            }
        }

        _activate(t) {
            this._activeTarget = t, this._clear();
            const e = Ut.split(",").map(e => `${e}[data-bs-target="${t}"],${e}[href="${t}"]`),
                i = V.findOne(e.join(","), this._config.target);
            i.classList.add("active"), i.classList.contains("dropdown-item") ? V.findOne(".dropdown-toggle", i.closest(".dropdown")).classList.add("active") : V.parents(i, ".nav, .list-group").forEach(t => {
                V.prev(t, ".nav-link, .list-group-item").forEach(t => t.classList.add("active")), V.prev(t, ".nav-item").forEach(t => {
                    V.children(t, ".nav-link").forEach(t => t.classList.add("active"))
                })
            }), H.trigger(this._scrollElement, "activate.bs.scrollspy", {relatedTarget: t})
        }

        _clear() {
            V.find(Ut, this._config.target).filter(t => t.classList.contains("active")).forEach(t => t.classList.remove("active"))
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Kt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t]()
                }
            }))
        }
    }

    H.on(window, "load.bs.scrollspy.data-api", () => {
        V.find('[data-bs-spy="scroll"]').forEach(t => new Kt(t))
    }), b(Kt);

    class Vt extends R {
        static get NAME() {
            return "tab"
        }

        show() {
            if (this._element.parentNode && this._element.parentNode.nodeType === Node.ELEMENT_NODE && this._element.classList.contains("active")) return;
            let t;
            const e = o(this._element),
                i = this._element.closest(".nav, .list-group");
            if (i) {
                const e = "UL" === i.nodeName || "OL" === i.nodeName ? ":scope > li > .active" : ".active";
                t = V.find(e, i), t = t[t.length - 1]
            }
            const s = t ? H.trigger(t, "hide.bs.tab", {relatedTarget: this._element}) : null;
            if (H.trigger(this._element, "show.bs.tab", {relatedTarget: t}).defaultPrevented || null !== s && s.defaultPrevented) return;
            this._activate(this._element, i);
            const n = () => {
                H.trigger(t, "hidden.bs.tab", {relatedTarget: this._element}), H.trigger(this._element, "shown.bs.tab", {relatedTarget: t})
            };
            e ? this._activate(e, e.parentNode, n) : n()
        }

        _activate(t, e, i) {
            const s = (!e || "UL" !== e.nodeName && "OL" !== e.nodeName ? V.children(e, ".active") : V.find(":scope > li > .active", e))[0],
                n = i && s && s.classList.contains("fade"),
                o = () => this._transitionComplete(t, s, i);
            s && n ? (s.classList.remove("show"), this._queueCallback(o, t, !0)) : o()
        }

        _transitionComplete(t, e, i) {
            if (e) {
                e.classList.remove("active");
                const t = V.findOne(":scope > .dropdown-menu .active", e.parentNode);
                t && t.classList.remove("active"), "tab" === e.getAttribute("role") && e.setAttribute("aria-selected", !1)
            }
            t.classList.add("active"), "tab" === t.getAttribute("role") && t.setAttribute("aria-selected", !0), p(t), t.classList.contains("fade") && t.classList.add("show");
            let s = t.parentNode;
            if (s && "LI" === s.nodeName && (s = s.parentNode), s && s.classList.contains("dropdown-menu")) {
                const e = t.closest(".dropdown");
                e && V.find(".dropdown-toggle", e).forEach(t => t.classList.add("active")), t.setAttribute("aria-expanded", !0)
            }
            i && i()
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Vt.getOrCreateInstance(this);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t]()
                }
            }))
        }
    }

    H.on(document, "click.bs.tab.data-api", '[data-bs-toggle="tab"], [data-bs-toggle="pill"], [data-bs-toggle="list"]', (function (t) {
        ["A", "AREA"].includes(this.tagName) && t.preventDefault(), d(this) || Vt.getOrCreateInstance(this).show()
    })), b(Vt);
    const Xt = {animation: "boolean", autohide: "boolean", delay: "number"},
        Yt = {animation: !0, autohide: !0, delay: 5e3};

    class Qt extends R {
        constructor(t, e) {
            super(t), this._config = this._getConfig(e), this._timeout = null, this._hasMouseInteraction = !1, this._hasKeyboardInteraction = !1, this._setListeners()
        }

        static get DefaultType() {
            return Xt
        }

        static get Default() {
            return Yt
        }

        static get NAME() {
            return "toast"
        }

        show() {
            H.trigger(this._element, "show.bs.toast").defaultPrevented || (this._clearTimeout(), this._config.animation && this._element.classList.add("fade"), this._element.classList.remove("hide"), p(this._element), this._element.classList.add("show"), this._element.classList.add("showing"), this._queueCallback(() => {
                this._element.classList.remove("showing"), H.trigger(this._element, "shown.bs.toast"), this._maybeScheduleHide()
            }, this._element, this._config.animation))
        }

        hide() {
            this._element.classList.contains("show") && (H.trigger(this._element, "hide.bs.toast").defaultPrevented || (this._element.classList.add("showing"), this._queueCallback(() => {
                this._element.classList.add("hide"), this._element.classList.remove("showing"), this._element.classList.remove("show"), H.trigger(this._element, "hidden.bs.toast")
            }, this._element, this._config.animation)))
        }

        dispose() {
            this._clearTimeout(), this._element.classList.contains("show") && this._element.classList.remove("show"), super.dispose()
        }

        _getConfig(t) {
            return t = {...Yt, ...K.getDataAttributes(this._element), ..."object" == typeof t && t ? t : {}}, c("toast", t, this.constructor.DefaultType), t
        }

        _maybeScheduleHide() {
            this._config.autohide && (this._hasMouseInteraction || this._hasKeyboardInteraction || (this._timeout = setTimeout(() => {
                this.hide()
            }, this._config.delay)))
        }

        _onInteraction(t, e) {
            switch (t.type) {
                case"mouseover":
                case"mouseout":
                    this._hasMouseInteraction = e;
                    break;
                case"focusin":
                case"focusout":
                    this._hasKeyboardInteraction = e
            }
            if (e) return void this._clearTimeout();
            const i = t.relatedTarget;
            this._element === i || this._element.contains(i) || this._maybeScheduleHide()
        }

        _setListeners() {
            H.on(this._element, "mouseover.bs.toast", t => this._onInteraction(t, !0)), H.on(this._element, "mouseout.bs.toast", t => this._onInteraction(t, !1)), H.on(this._element, "focusin.bs.toast", t => this._onInteraction(t, !0)), H.on(this._element, "focusout.bs.toast", t => this._onInteraction(t, !1))
        }

        _clearTimeout() {
            clearTimeout(this._timeout), this._timeout = null
        }

        static jQueryInterface(t) {
            return this.each((function () {
                const e = Qt.getOrCreateInstance(this, t);
                if ("string" == typeof t) {
                    if (void 0 === e[t]) throw new TypeError(`No method named "${t}"`);
                    e[t](this)
                }
            }))
        }
    }

    return F(Qt), b(Qt), {
        Alert: W,
        Button: $,
        Carousel: et,
        Collapse: nt,
        Dropdown: pt,
        Modal: Tt,
        Offcanvas: Lt,
        Popover: Wt,
        ScrollSpy: Kt,
        Tab: Vt,
        Toast: Qt,
        Tooltip: Bt
    }
}));
//# sourceMappingURL=bootstrap.js.map
