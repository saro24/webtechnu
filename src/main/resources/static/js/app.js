const images = gsap.utils.toArray(".image");
const height = window.innerHeight;

gsap.set(".gallery", { y: `${height / 2}px` });

gsap.to(".col:first-of-type", {
  y: () => `-${(height * images.length) / 2}px`,
  duration: 80,
  ease: "none",
  repeat: -1,
});

gsap.to(".col:last-of-type", {
   y: () => `-${(height * images.length) / 2}px`,
  duration: 80,
  ease: "none",
  repeat: -1
});

gsap.to(".col:nth-of-type(2)", {
 y: () => `${(height * images.length) / 2}px`,
  duration: 80,
  ease: "none",
  repeat: -1,
});

class Example {
    constructor(options) {
        this.root = options.root;

        this.init();

        setTimeout(this.showImages.bind(this), 1000);
    }

    init() {
        this.scroll = new LocomotiveScroll({
            el: this.root,
            direction: 'horizontal',
            smooth: true,
            lerp: 0.05,
            tablet: {
                smooth: true
            },
            smartphone: {
                smooth: true
            }
        });

        this.images = this.root.querySelectorAll('.image');

        [].forEach.call(this.images, (image) => {
            image.addEventListener('click', () => {
                image.classList.add('-clicked');
                this.hideImages();
            });
        });
    }

    showImages() {
        [].forEach.call(this.images, (image) => {
            image.classList.remove('-clicked');
            image.classList.add('-active');
        });
    }

    hideImages() {
        [].forEach.call(this.images, (image) => {
            image.classList.remove('-active');
        });

        setTimeout(this.showImages.bind(this), 2000);
    }
}


window.addEventListener('DOMContentLoaded', (event) => {
    const example = new Example({
        root: document.querySelector('.scroll-animations-example')
    });
});
