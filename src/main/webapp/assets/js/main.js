const footer = document.querySelector(".site-footer");
const reduceMotion = window.matchMedia(
    "(prefers-reduced-motion: reduce)"
).matches;

if (footer && !reduceMotion && "IntersectionObserver" in window) {
    document.documentElement.classList.add("has-scroll-reveal");

    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                footer.classList.add("is-visible");
                observer.unobserve(footer);
            }
        });
    }, { threshold: 0.08 });

    observer.observe(footer);
} else if (footer) {
    footer.classList.add("is-visible");
}