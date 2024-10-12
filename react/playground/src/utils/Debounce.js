export function debounce(callback, delay) {
    let timeoutId = null;

    return function (...args) {
        const context = this;
        clearTimeout(timeoutId);

        timeoutId = setTimeout(() => {
            timeoutId = null;
            callback.apply(context, args);
        }, delay);
    };
}